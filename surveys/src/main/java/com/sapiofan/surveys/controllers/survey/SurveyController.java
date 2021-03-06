package com.sapiofan.surveys.controllers.survey;


import com.sapiofan.surveys.entities.survey.*;
import com.sapiofan.surveys.services.survey.StatisticsService;
import com.sapiofan.surveys.services.survey.SurveyQuestionService;
import com.sapiofan.surveys.services.survey.SurveyResultsService;
import com.sapiofan.surveys.services.survey.SurveyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


@Controller
public class SurveyController {
    @Autowired
    private SurveyService surveyService;

    @Autowired
    private SurveyResultsService surveyResultsService;

    @Autowired
    private SurveyQuestionService surveyQuestionService;

    @Autowired
    private StatisticsService statisticsService;

    private static final Logger log = LoggerFactory.getLogger("log");

    @PostMapping(value = "/listOfQuestions", params = "continue")
    public String addSurvey(@RequestParam("name") String name,
                            @RequestParam("surveyId") Long surveyId,
                            @RequestParam("description") String description,
                            Authentication authentication,
                            Model model) {
        Survey survey = surveyService.createSurvey(surveyId, name, description, authentication);
        model.addAttribute("questions", surveyQuestionService.findAllQuestions(survey.getId()));
        model.addAttribute("surveyId", survey.getId());
        model.addAttribute("questionId", 0);
        return "listOfQuestions";
    }

    @PostMapping(value = "/listOfQuestions", params = "returnToQuestions")
    public String returnToQuestions(Model model,
                                    @RequestParam("surveyId") Long surveyId,
                                    @RequestParam("questionId") Long questionId) {
        Question question = surveyQuestionService.findQuestionById(questionId);
        int counter = 0;
        if (questionId != 0) {
            for (Answer answer : question.getAnswers()) {
                if (answer.getCorrectness()) {
                    counter++;
                }
            }
        }

        if (questionId != 0 && (question.getAnswers().size() < 2 || counter != 1)) {
            surveyQuestionService.deleteQuestionByNumber(surveyId, question.getId());
        }
        model.addAttribute("surveyId", surveyId);
        model.addAttribute("questionId", 0);
        List<Question> questions = surveyQuestionService.findAllQuestions(surveyId);
        model.addAttribute("questions", questions);
        model.addAttribute("size", questions.size());
        return "listOfQuestions";
    }

    @PostMapping(value = "/survey", params = "changeSurveyName")
    public String changeSurveyName(@RequestParam(name = "surveyId") Long surveyId, Model model) {
        Survey survey = surveyService.findSurveyById(surveyId);
        model.addAttribute("surveyId", surveyId);
        model.addAttribute("name", survey.getName());
        model.addAttribute("description", survey.getDescription());
        return "survey";
    }

    @PostMapping(value = "/main", params = "backToMain")
    public String backToMain(@RequestParam("surveyId") Long surveyId) {
        if (surveyId != 0 && surveyService.findSurveyById(surveyId).getQuestions().size() != 0) {
            surveyService.deleteSurveyById(surveyId);
        }
        return "main";
    }

    @PostMapping(value = "/list", params = "saveSurvey")
    public String saveSurvey(Model model, @RequestParam("surveyId") Long surveyId) {
        Survey survey = surveyService.findSurveyById(surveyId);
        survey.setSize(survey.getQuestions().size());
        surveyService.save(survey);
        model.addAttribute("surveys", surveyService.findAllSurveys());
        return "list";
    }

    @PostMapping(value = "/main", params = "deleteSurvey")
    public String deleteSurvey(@RequestParam("surveyId") Long surveyId) {
        surveyService.deleteSurveyById(surveyId);
        return "main";
    }

    @GetMapping("/survey/{id}")
    public String startPageSurvey(@PathVariable("id") Long id, Model model) {
        Survey survey = surveyService.findSurveyById(id);
        model.addAttribute("surveyId", id);
        model.addAttribute("name", survey.getName());
        model.addAttribute("question", surveyQuestionService.findQuestionByNumber(id, 1));
        model.addAttribute("description", survey.getDescription());
        return "startSurvey";
    }

    @PostMapping(value = "/survey/{id}", params = "start")
    public String startSurvey(@PathVariable("id") Long id, Model model, Authentication authentication) {
        model.addAttribute("surveyId", id);
        SurveyResults surveyResults = surveyResultsService.createSurveyResults(authentication, id);
        model.addAttribute("resultId", surveyResults.getId());
        Question question = surveyQuestionService.findQuestionByNumber(id, 1);
        model.addAttribute("question", question);
        model.addAttribute("answers", question.getAnswers().stream().sorted(Comparator.comparingInt(Answer::getNumber)).collect(Collectors.toList()));
        model.addAttribute("questions", surveyQuestionService.findAllQuestions(id));
        model.addAttribute("size", question.getAnswers().size());
        return "passSurvey";
    }

    @GetMapping(value = "/survey/{id}/{number}", params = "back")
    public String backQuestion(@PathVariable("id") Long id, @PathVariable("number") Integer number,
                               @RequestParam("resultId") UUID resultId,
                               Model model) {
        if (number - 1 == 0) {
            surveyResultsService.deleteResultsById(resultId);
            model.addAttribute("surveys", surveyService.findAllSurveys());
            return "list";
        }
        Survey survey = surveyService.findSurveyById(id);
        model.addAttribute("surveyId", id);
        model.addAttribute("resultId", resultId);
        if (number - 1 > 0) {
            Question question1 = surveyQuestionService.findQuestionByNumber(id, number - 1);
            List<Answer> answers = question1.getAnswers();
            model.addAttribute("questions", survey.getQuestions().size());
            model.addAttribute("question", question1);
            model.addAttribute("answers", answers);
            model.addAttribute("size", answers.size());
        }
        model.addAttribute("questions", surveyQuestionService.findAllQuestions(id));
        return "passSurvey";
    }

    @GetMapping(value = "/survey/{id}/{number}")
    public String getQuestion(@PathVariable("id") Long id, @PathVariable("number") Integer number,
                              @RequestParam("resultId") UUID resultId,
                              @RequestParam(value = "radio", required = false) String answer,
                              Model model) {
        Survey survey = surveyService.findSurveyById(id);
        SurveyResults results = surveyResultsService.findSurveyResultsById(resultId);
        RightAnswers rightAnswers = new RightAnswers();
        Question question = surveyQuestionService.findQuestionByNumber(id, number);
        for (int i = 0; i < question.getAnswers().size(); i++) {
            Answer answer1 = question.getAnswers().get(i);
            if (answer.equals(String.valueOf(answer1.getNumber()))) {
                rightAnswers.addQuestion(question, answer1.getCorrectness());
                rightAnswers.setResults(results);
                statisticsService.saveResult(rightAnswers);
                break;
            }
        }
        model.addAttribute("surveyId", id);
        model.addAttribute("resultId", resultId);
        if (number + 1 <= survey.getQuestions().size()) {
            Question question1 = surveyQuestionService.findQuestionByNumber(id, number + 1);
            List<Answer> answers = question1.getAnswers();
            model.addAttribute("questions", survey.getQuestions().size());
            model.addAttribute("question", question1);
            model.addAttribute("answers", answers);
        }
        if (survey.getQuestions().size() == number) {
            Timestamp ts = Timestamp.from(Instant.now());
            results.setEnd_time(ts);
            long time_difference = ts.getTime() - results.getStart().getTime();
            long hours_difference = TimeUnit.MILLISECONDS.toHours(time_difference) % 24;
            long minutes_difference = TimeUnit.MILLISECONDS.toMinutes(time_difference) % 60;
            long seconds_difference = TimeUnit.MILLISECONDS.toSeconds(time_difference) % 60;

            List<RightAnswers> rightAnswersList = statisticsService.results(results.getId());
            int counter = 0;
            for (RightAnswers rightAnswersElement : rightAnswersList) {
                if (rightAnswersElement.getaBoolean())
                    counter++;
            }
            model.addAttribute("allAnswers", rightAnswersList.size());
            model.addAttribute("counter", counter);
            model.addAttribute("hours", hours_difference);
            model.addAttribute("minutes", minutes_difference);
            model.addAttribute("seconds", seconds_difference);
            surveyResultsService.deleteResultsById(resultId);
            return "statistics";
        }
        model.addAttribute("questions", surveyQuestionService.findAllQuestions(id));
        model.addAttribute("size", question.getAnswers().size());
        return "passSurvey";
    }
}
