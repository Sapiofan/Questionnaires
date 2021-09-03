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
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("minimum", 1);
        model.addAttribute("maximum", max * questionnaire.getQuestions().size());
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
        Description description = new Description();
        description.setDescription(inputtedDescription);
        description.setNumber(questionnaire.getDescriptions().size()+1);
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
    public String saveQuestionnaire(){
        return "list";
    }

    @GetMapping("/deleteDescription/{number}")
    public String deleteDescriptionById(@PathVariable("number") Integer number,
                                      @RequestParam("questionnaireId") Long questionnaireId,
                                        @RequestParam("minimum") Integer minimum,
                                        @RequestParam("maximum") Integer maximum,
                                      Model model){
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
        Description description = questionnaire.getDescriptions().get(number-1);
        for (int i = 0; i < questionnaire.getDescriptions().size() - description.getNumber(); i++) {
            Description description1 = questionnaire.getDescriptions().get(number);
            description1.setNumber(description1.getNumber() - 1);
            questionnaireService.saveDescription(description1);
        }
        questionnaireService.deleteDescriptionById(description.getId());
        questionnaireService.saveQuestionnaire(questionnaire);
        model.addAttribute("questionnaireId", questionnaireId);
        model.addAttribute("questions", questionnaireService.findAllQuestions(questionnaireId));
        model.addAttribute("minimum", minimum);
        model.addAttribute("maximum", maximum);
        return "descriptions";
    }

}
