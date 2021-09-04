package com.sapiofan.surveys.controllers;

import com.sapiofan.surveys.entities.*;
import com.sapiofan.surveys.security.realization.CustomUserDetails;
import com.sapiofan.surveys.services.impl.QuestionnaireServiceImpl;
import com.sapiofan.surveys.services.impl.UserServiceImpl;
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
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
public class QuestionnaireController {

    Logger logger = LoggerFactory.getLogger(QuestionnaireController.class);

    @Autowired
    private QuestionnaireServiceImpl questionnaireService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/createQuestionnaire")
    public String questionnaireForm(Model model){
        model.addAttribute("questionnaireId", 0);
        return "questionnaire";
    }

    @PostMapping(value = "/createQuestionnaire", params = "backToMain")
    public String returnToMainPage(@RequestParam("questionnaireId") Long questionnaireId){
        if(questionnaireId != 0)
            questionnaireService.deleteQuestionnaire(questionnaireId);
        return "main";
    }

    @PostMapping(value = "/createQuestionnaire")
    public String createQuestionnaire(@RequestParam("name") String name,
                                      @RequestParam("description") String description,
                                      @RequestParam("questionnaireId") Long questionnaireId,
                                      @RequestParam("scale") Integer scale,
                                      Authentication authentication,
                                      Model model){
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        Questionnaire questionnaire;
        if(questionnaireId == 0) {
            questionnaire = new Questionnaire();
            questionnaire.setUser(userService.findUserByNickname(principal.getUsername()));
        }
        else{
            questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
        }
        questionnaire.setName(name);
        questionnaire.setGeneral_description(description);
        questionnaire.setSize(0);
        if(scale == 5){
            questionnaire.setScale(Scale.FIVE);
        }
        else {
            questionnaire.setScale(Scale.TEN);
        }

        questionnaireService.saveQuestionnaire(questionnaire);
        model.addAttribute("questions", questionnaireService.findAllQuestions(questionnaire.getId()));
        model.addAttribute("questionnaireId", questionnaire.getId());
        model.addAttribute("questionId", 0);
        return "questionnaireQuestions";
    }


    @GetMapping(value = "/addQQuestion", params = "changeQuestionnaireFields")
    public String changeFields(@RequestParam("questionnaireId") Long questionnaireId,
                               Model model){
        model.addAttribute("questionnaireId", questionnaireId);
        return "questionnaire";
    }

    @GetMapping(value = "/addQQuestion", params = "deleteQuestionnaire")
    public String deleteQuestionnaire(@RequestParam("questionnaireId") Long questionnaireId){
        questionnaireService.deleteQuestionnaire(questionnaireId);
        return "main";
    }

    @GetMapping(value = "/addQQuestion", params = "add")
    public String addQQuestion(@RequestParam("questionnaireId") Long questionnaireId,
                               @RequestParam("question") String inputtedQuestion,
                               Model model){
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
        QQuestion question = new QQuestion();
        question.setNumber(questionnaire.getQuestions().size()+1);
        question.setName(inputtedQuestion);
        question.setQuestionnaire(questionnaire);
        questionnaireService.saveQQuestion(question);
        model.addAttribute("questionnaireId", questionnaireId);
        model.addAttribute("questions", questionnaireService.findAllQuestions(questionnaireId));
        return "questionnaireQuestions";
    }

    @GetMapping(value = "/addQQuestion", params = "addDescriptions")
    public String goToDescriptions(Model model, @RequestParam("questionnaireId") Long questionnaireId){
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
        questionnaire.setSize(questionnaire.getQuestions().size());
        questionnaireService.saveQuestionnaire(questionnaire);
        int max;
        if(questionnaire.getScale().equals(Scale.FIVE))
            max = 5;
        else
            max = 10;
        model.addAttribute("questionnaireId", questionnaireId);
        List<Description> descriptions = questionnaireService.findAllDescriptions(questionnaireId);
        int minimum = 1;
        for (int i = 0; i < descriptions.size(); i++) {
            if(minimum < descriptions.get(i).getEnd_scale())
                minimum = descriptions.get(i).getEnd_scale()+1;
        }
        model.addAttribute("minimum", minimum);
        model.addAttribute("maximum", max * questionnaire.getQuestions().size());
        model.addAttribute("descriptions", questionnaireService.findAllDescriptions(questionnaireId));
        return "descriptions";
    }

    @GetMapping("/deleteQQuestion/{number}")
    public String deleteQQuestionById(@PathVariable("number") Integer number,
                                     @RequestParam("questionnaireId") Long questionnaireId,
                                     Model model){
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
        QQuestion question = questionnaire.getQuestions().get(number-1);
        for (int i = 0; i < questionnaire.getQuestions().size() - question.getNumber(); i++) {
            QQuestion question1 = questionnaire.getQuestions().get(number);
            question1.setNumber(question1.getNumber() - 1);
            questionnaireService.saveQQuestion(question1);
        }
        questionnaireService.deleteQQuestionById(question.getId());
        questionnaireService.saveQuestionnaire(questionnaire);
        model.addAttribute("questionnaireId", questionnaireId);
        model.addAttribute("questions", questionnaireService.findAllQuestions(questionnaireId));
        return "questionnaireQuestions";
    }


    @GetMapping(value = "/addDescription", params = "addQuestions")
    public String addQuestions(@RequestParam("questionnaireId") Long questionnaireId,
                               Model model){
        model.addAttribute("questionnaireId", questionnaireId);
        model.addAttribute("questions", questionnaireService.findAllQuestions(questionnaireId));
        return "questionnaireQuestions";
    }

    @GetMapping(value = "/addDescription", params = "deleteQuestionnaire")
    public String deleteQuestionnaireFromDescription(@RequestParam("questionnaireId") Long questionnaireId){
        questionnaireService.deleteQuestionnaire(questionnaireId);
        return "main";
    }

    @GetMapping(value = "/addDescription", params = "addDescription")
    public String addDescription(@RequestParam("questionnaireId") Long questionnaireId,
                                 @RequestParam("description") String inputtedDescription,
                                 @RequestParam("range") Integer range,
                                 @RequestParam("minimum") Integer minimum,
                                 @RequestParam("maximum") Integer maximum,
                                 Model model
                                 ){
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
        List<Description> descriptionList = questionnaire.getDescriptions();

        for (Description value : descriptionList) {
            if (value.getDescription().equals(inputtedDescription)) {
                model.addAttribute("questionnaireId", questionnaireId);
                model.addAttribute("minimum", minimum);
                model.addAttribute("maximum", maximum);
                model.addAttribute("descriptions", questionnaireService.findAllDescriptions(questionnaireId));
                return "descriptions";
            }
        }
        Description description = new Description();
        description.setDescription(inputtedDescription);
        description.setNumber(questionnaire.getDescriptions().size() + 1);
        description.setQuestionnaire(questionnaire);
        description.setStart_scale(minimum);
        description.setEnd_scale(range);
        questionnaireService.saveDescription(description);

        model.addAttribute("questionnaireId", questionnaireId);
        model.addAttribute("minimum", range+1);
        model.addAttribute("maximum", maximum);
        model.addAttribute("descriptions", questionnaireService.findAllDescriptions(questionnaireId));
        return "descriptions";
    }

    @GetMapping(value = "/addDescription", params = "saveQuestionnaire")
    public String saveQuestionnaire(Model model){
        model.addAttribute(questionnaireService.findAllQuestionnaires());
        return "listOfQuestionnaires";
    }

    @GetMapping("/deleteDescription/{id}")
    public String deleteDescriptionById(@PathVariable("id") Long descriptionId,
                                      @RequestParam("questionnaireId") Long questionnaireId,
                                      Model model){
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
        Description description = questionnaireService.findDescriptionById(descriptionId);
        if(description != null) {
            int number = description.getNumber();
            int diff = description.getEnd_scale() - description.getStart_scale() + 1;

            if (number - 2 >= 0 && number < questionnaire.getDescriptions().size()) {
                Description before = questionnaire.getDescriptions().get(number - 2);
                Description after = questionnaire.getDescriptions().get(number);
                after.setStart_scale(after.getStart_scale() - diff / 2);
                questionnaireService.saveDescription(before);
                questionnaireService.saveDescription(after);
                if ((diff % 2 == 1)) {
                    before.setEnd_scale(before.getEnd_scale() + diff / 2 + 1);
                } else {
                    before.setEnd_scale(before.getEnd_scale() + diff / 2);
                }
            } else if (number - 2 < 0) {
                Description after = questionnaire.getDescriptions().get(number);
                after.setStart_scale(1);
                questionnaireService.saveDescription(after);
            } else if (number >= questionnaire.getDescriptions().size()) {
                Description before = questionnaire.getDescriptions().get(number - 2);
                before.setEnd_scale(description.getEnd_scale());
                questionnaireService.saveDescription(before);
            }

            for (int i = 0; i < questionnaire.getDescriptions().size() - description.getNumber(); i++) {
                Description description1 = questionnaire.getDescriptions().get(number);
                description1.setNumber(description1.getNumber() - 1);
                questionnaireService.saveDescription(description1);
            }
            questionnaireService.deleteDescriptionById(description.getId());
            questionnaireService.saveQuestionnaire(questionnaire);
        }

        List<Description> descriptions = questionnaireService.findAllDescriptions(questionnaireId);
        int minimum = minimum(descriptions);
        int max = maximum(questionnaire);
        model.addAttribute("questionnaireId", questionnaireId);
        model.addAttribute("descriptions", questionnaireService.findAllDescriptions(questionnaireId));
        model.addAttribute("minimum", minimum);
        model.addAttribute("maximum", max);
        return "descriptions";
    }

    @GetMapping("/editDescription/{id}")
    public String editDescriptionForm(@PathVariable("id") Long descriptionId,
                                  @RequestParam("questionnaireId") Long questionnaireId,
                                  Model model){
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
        List<Description> descriptions = questionnaireService.findAllDescriptions(questionnaireId);
        Description description = questionnaireService.findDescriptionById(descriptionId);
        int number = description.getNumber();
        int minimum, max;
        if(number - 2 >= 0 && number < descriptions.size()) {
            Description before = descriptions.get(number - 2);
            Description after = descriptions.get(number);
            minimum = before.getStart_scale() + 1;
            max = after.getEnd_scale() - 1;
        }
        else if(number - 2 < 0){
            Description after = descriptions.get(number);
            minimum = 2;
            max = after.getEnd_scale() - 1;
        }
        else if(number >= descriptions.size()){
            Description before = descriptions.get(number - 2);
            minimum = before.getStart_scale() + 1;
            max = maximum(questionnaire);
        }
        else{
            minimum = 1;
            max = maximum(questionnaire);
        }
        model.addAttribute("descriptionId", descriptionId);
        model.addAttribute("questionnaireId", questionnaireId);
        model.addAttribute("minimum", minimum);
        model.addAttribute("maximum", max);
        model.addAttribute("value", description.getEnd_scale());
        model.addAttribute("value_min", description.getStart_scale());
        model.addAttribute("description", description);
        return "editDescription";
    }

    @PostMapping("/addDescription")
    public String editDescription(@RequestParam("questionnaireId") Long questionnaireId,
                                  @RequestParam("description") String inputtedDescription,
                                  @RequestParam("range1") Integer rangeLow,
                                  @RequestParam("range2") Integer rangeHigh,
                                  @RequestParam("minimum") Integer minimum,
                                  @RequestParam("maximum") Integer maximum,
                                  Model model){
        model.addAttribute("questionnaireId", questionnaireId);
        return "descriptions";
    }

    @GetMapping("/questionnaire/{id}")
    public String startPageQuestionnaire(@PathVariable("id") Long id, Model model) {
        model.addAttribute("questionnaireId", id);
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(id);
        questionnaireService.saveQuestionnaire(questionnaire);
        model.addAttribute("question", questionnaireService.findQuestionByNumber(id, 1));
        model.addAttribute("description", questionnaire.getGeneral_description());
        return "startQuestionnaire";
    }

    @PostMapping(value = "/questionnaire/{id}", params = "start")
    public String startQuestionnaire(@PathVariable("id") Long id, Model model, Authentication authentication) {
        model.addAttribute("questionnaireId", id);
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(id);

        QuestionnaireResult result = new QuestionnaireResult();
        result.setQuestionnaire(questionnaire);
        result.setUser(userService.findUserByNickname(principal.getUsername()));
        Timestamp ts = Timestamp.from(Instant.now());
        result.setStart(ts);
        result.setEnd_time(ts);
        questionnaireService.saveQuestionnaireResult(result);
        model.addAttribute("resultId", result.getId());
        QQuestion question = questionnaireService.findQuestionByNumber(id, 1);
        model.addAttribute("question", question);
        model.addAttribute("maximum", 10);
        model.addAttribute("minimum", 1);
        model.addAttribute("middle", 5);
        return "passQuestionnaire";
    }

    @GetMapping(value = "/questionnaire/{id}/{number}", params = "back")
    public String backQuestion(@PathVariable("id") Long id, @PathVariable("number") Integer number,
                               @RequestParam("resultId") UUID resultId,
                               Model model){
        if(number - 1 == 0){
            questionnaireService.deleteResultsById(resultId);
            model.addAttribute("questionnaires", questionnaireService.findAllQuestionnaires());
            return "listOfQuestionnaires";
        }
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(id);
        model.addAttribute("questionnaireId", id);
        model.addAttribute("resultId", resultId);
        if(number-1 > 0 ) {
            QQuestion question = questionnaireService.findQuestionByNumber(id, number - 1);
            model.addAttribute("question", question);
        }
        model.addAttribute("maximum", 10);
        model.addAttribute("minimum", 1);
        model.addAttribute("middle", 5);
        return "passQuestionnaire";
    }

    @GetMapping(value = "/questionnaire/{id}/{number}", params = "next")
    public String getQuestion(@PathVariable("id") Long id, @PathVariable("number") Integer number, //fix updating of page
                              @RequestParam("resultId") UUID resultId,
                              @RequestParam("range") Integer answer,
                              Model model) {
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(id);
        QuestionnaireResult result = questionnaireService.findQuestionnaireResultById(resultId);
        EvaluatedQuestion evaluatedQuestion = new EvaluatedQuestion();
        QQuestion question = questionnaireService.findQuestionByNumber(id, number);
        evaluatedQuestion.setQuestion(question);
        evaluatedQuestion.setGrade(answer);
        evaluatedQuestion.setResults(result);
        questionnaireService.saveEvaluatedQuestion(evaluatedQuestion);

        model.addAttribute("questionnaireId", id);
        model.addAttribute("resultId", resultId);
        if(number+1 <= questionnaire.getQuestions().size()) {
            QQuestion question1 = questionnaireService.findQuestionByNumber(id, number + 1);
            model.addAttribute("question", question1);
        }
        if (questionnaire.getQuestions().size() == number) {
            Timestamp ts = Timestamp.from(Instant.now());
            result.setEnd_time(ts);
            long time_difference = ts.getTime() - result.getStart().getTime();
            long hours_difference = TimeUnit.MILLISECONDS.toHours(time_difference) % 24;
            long minutes_difference = TimeUnit.MILLISECONDS.toMinutes(time_difference) % 60;
            long seconds_difference = TimeUnit.MILLISECONDS.toSeconds(time_difference) % 60;

            List<EvaluatedQuestion> evaluatedQuestionList = questionnaireService.findAllEvaluatedQuestions(result.getId());
            int counter = 0;
            for (EvaluatedQuestion evaluatedQuestion1 : evaluatedQuestionList) {
                counter += evaluatedQuestion1.getGrade();
            }
            List<Description> descriptions = questionnaire.getDescriptions();
            String mainDescription = "";
            for (Description description : descriptions) {
                if(counter >= description.getStart_scale() && counter <= description.getEnd_scale()){
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
        model.addAttribute("maximum", 10);
        model.addAttribute("minimum", 1);
        model.addAttribute("middle", 5);
        return "passQuestionnaire";
    }

    @PostMapping(value = "/listOfQuestionnaires", params = "back")
    public String backQuestionnaires(@RequestParam("resultId") UUID resultId, Model model){
        questionnaireService.deleteResultsById(resultId);
        model.addAttribute("questionnaires", questionnaireService.findAllQuestionnaires());
        return "listOfQuestionnaires";
    }

    private int minimum(List<Description> descriptions){
        int minimum = 1;
        for (int i = 0; i < descriptions.size(); i++) {
            if(minimum < descriptions.get(i).getEnd_scale())
                minimum = descriptions.get(i).getEnd_scale()+1;
        }
        return minimum;
    }

    private int maximum(Questionnaire questionnaire){
        int max;
        if(questionnaire.getScale().equals(Scale.FIVE))
            max = 5;
        else
            max = 10;
        max *= questionnaire.getQuestions().size();
        return max;
    }
}
