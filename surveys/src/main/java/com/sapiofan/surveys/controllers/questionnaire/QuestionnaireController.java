package com.sapiofan.surveys.controllers.questionnaire;

import com.sapiofan.surveys.entities.questionnaire.*;
import com.sapiofan.surveys.services.questionnaire.QuestionnaireQuestionsService;
import com.sapiofan.surveys.services.questionnaire.QuestionnaireResultsService;
import com.sapiofan.surveys.services.questionnaire.QuestionnaireService;
import com.sapiofan.surveys.services.questionnaire.QuestionnaireStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
public class QuestionnaireController {

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private QuestionnaireQuestionsService questionnaireQuestionsService;

    @Autowired
    private QuestionnaireResultsService questionnaireResultsService;

    @Autowired
    private QuestionnaireStatisticsService questionnaireStatisticsService;

    @GetMapping("/createQuestionnaire")
    public String questionnaireForm(Model model) {
        model.addAttribute("questionnaireId", 0);
        return "questionnaire";
    }

    @PostMapping(value = "/main", params = "backToMainQ")
    public String returnToMainPage(@RequestParam("questionnaireId") Long questionnaireId) {
        if (questionnaireId != 0)
            questionnaireService.deleteQuestionnaire(questionnaireId);
        return "main";
    }

    @PostMapping(value = "/questionnaireQuestions")
    public String createQuestionnaire(@RequestParam("name") String name,
                                      @RequestParam("description") String description,
                                      @RequestParam("questionnaireId") Long questionnaireId,
                                      @RequestParam("scale") Integer scale,
                                      Authentication authentication,
                                      Model model) {
        Questionnaire questionnaire = questionnaireService.createQuestionnaire(authentication, questionnaireId, name,
                description, scale);
        model.addAttribute("questions", questionnaireQuestionsService.findAllQuestions(questionnaire.getId()));
        model.addAttribute("questionnaireId", questionnaire.getId());
        model.addAttribute("questionId", 0);
        return "questionnaireQuestions";
    }

    @GetMapping("/questionnaire/{id}")
    public String startPageQuestionnaire(@PathVariable("id") Long id, Model model) {
        model.addAttribute("questionnaireId", id);
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(id);
        questionnaireService.saveQuestionnaire(questionnaire);
        model.addAttribute("question", questionnaireQuestionsService.findQuestionByNumber(id, 1));
        model.addAttribute("description", questionnaire.getGeneral_description());
        model.addAttribute("name", questionnaire.getName());
        return "startQuestionnaire";
    }

    @PostMapping(value = "/questionnaire/{id}", params = "start")
    public String startQuestionnaire(@PathVariable("id") Long id, Model model, Authentication authentication) {
        model.addAttribute("questionnaireId", id);
        QuestionnaireResult questionnaireResult = questionnaireResultsService.createQuestionnaireResult(authentication, id);
        model.addAttribute("resultId", questionnaireResult.getId());
        QQuestion question = questionnaireQuestionsService.findQuestionByNumber(id, 1);
        model.addAttribute("question", question);
        if(questionnaireService.findQuestionnaireById(id).getScale().equals(Scale.FIVE)) {
            model.addAttribute("maximum", 5);
            model.addAttribute("minimum", 1);
            model.addAttribute("middle", 3);
        }
        else {
            model.addAttribute("maximum", 10);
            model.addAttribute("minimum", 1);
            model.addAttribute("middle", 5);
        }
        return "passQuestionnaire";
    }

    @GetMapping(value = "/questionnaire/{id}/{number}", params = "back")
    public String backQuestion(@PathVariable("id") Long id, @PathVariable("number") Integer number,
                               @RequestParam("resultId") UUID resultId,
                               Model model) {
        if (number - 1 == 0) {
            questionnaireResultsService.deleteResultsById(resultId);
            model.addAttribute("questionnaires", questionnaireService.findAllQuestionnaires());
            return "listOfQuestionnaires";
        }
        model.addAttribute("questionnaireId", id);
        model.addAttribute("resultId", resultId);
        if (number - 1 > 0) {
            QQuestion question = questionnaireQuestionsService.findQuestionByNumber(id, number - 1);
            model.addAttribute("question", question);
        }
        if(questionnaireService.findQuestionnaireById(id).getScale().equals(Scale.FIVE)) {
            model.addAttribute("maximum", 5);
            model.addAttribute("minimum", 1);
            model.addAttribute("middle", 3);
        }
        else {
            model.addAttribute("maximum", 10);
            model.addAttribute("minimum", 1);
            model.addAttribute("middle", 5);
        }
        return "passQuestionnaire";
    }

    @GetMapping(value = "/questionnaire/{id}/{number}", params = "next")
    public String getQuestion(@PathVariable("id") Long id, @PathVariable("number") Integer number,
                              @RequestParam("resultId") UUID resultId,
                              @RequestParam("range") Integer answer,
                              Model model) {
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(id);
        QuestionnaireResult result = questionnaireResultsService.findQuestionnaireResultById(resultId);
        EvaluatedQuestion evaluatedQuestion = new EvaluatedQuestion();
        QQuestion question = questionnaireQuestionsService.findQuestionByNumber(id, number);
        evaluatedQuestion.setQuestion(question);
        evaluatedQuestion.setGrade(answer);
        evaluatedQuestion.setResults(result);
        questionnaireStatisticsService.saveEvaluatedQuestion(evaluatedQuestion);

        model.addAttribute("questionnaireId", id);
        model.addAttribute("resultId", resultId);
        if (number + 1 <= questionnaire.getQuestions().size()) {
            QQuestion question1 = questionnaireQuestionsService.findQuestionByNumber(id, number + 1);
            model.addAttribute("question", question1);
        }
        if (questionnaire.getQuestions().size() == number) {
            Timestamp ts = Timestamp.from(Instant.now());
            result.setEnd_time(ts);
            long time_difference = ts.getTime() - result.getStart().getTime();
            long hours_difference = TimeUnit.MILLISECONDS.toHours(time_difference) % 24;
            long minutes_difference = TimeUnit.MILLISECONDS.toMinutes(time_difference) % 60;
            long seconds_difference = TimeUnit.MILLISECONDS.toSeconds(time_difference) % 60;

            List<EvaluatedQuestion> evaluatedQuestionList = questionnaireStatisticsService.findAllEvaluatedQuestions(result.getId());
            int counter = 0;
            for (EvaluatedQuestion evaluatedQuestion1 : evaluatedQuestionList) {
                counter += evaluatedQuestion1.getGrade();
            }
            List<Description> descriptions = questionnaire.getDescriptions();
            String mainDescription = "";
            for (Description description : descriptions) {
                if (counter >= description.getStart_scale() && counter <= description.getEnd_scale()) {
                    mainDescription += description.getDescription();
                    break;
                }
            }
            model.addAttribute("description", mainDescription);
            model.addAttribute("hours", hours_difference);
            model.addAttribute("minutes", minutes_difference);
            model.addAttribute("seconds", seconds_difference);
            return "questionnaireStatistics";
        }
        if(questionnaireService.findQuestionnaireById(id).getScale().equals(Scale.FIVE)) {
            model.addAttribute("maximum", 5);
            model.addAttribute("minimum", 1);
            model.addAttribute("middle", 3);
        }
        else {
            model.addAttribute("maximum", 10);
            model.addAttribute("minimum", 1);
            model.addAttribute("middle", 5);
        }
        return "passQuestionnaire";
    }

    @PostMapping(value = "/listOfQuestionnaires", params = "back")
    public String backQuestionnaires(@RequestParam("resultId") UUID resultId, Model model) {
        questionnaireResultsService.deleteResultsById(resultId);
        model.addAttribute("questionnaires", questionnaireService.findAllQuestionnaires());
        return "listOfQuestionnaires";
    }
}
