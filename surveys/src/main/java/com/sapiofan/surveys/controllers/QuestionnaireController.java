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
@RequestMapping("/")
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
    public String returnToMainPage(){
        return "main";
    }

    @PostMapping(value = "/createQuestionnaire")
    public String createQuestionnaire(@RequestParam("name") String name,
                                      @RequestParam("description") String description,

                                      @RequestParam("scale") String scale,
                                      Authentication authentication,
                                      Model model){
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        Questionnaire questionnaire;
//        if(questionnaireId == 0) {
            questionnaire = new Questionnaire();
            questionnaire.setUser(userService.findUserByNickname(principal.getUsername()));
//        }
//        else{
//            questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
//        }
        questionnaire.setName(name);
        questionnaire.setGeneral_description(description);
        questionnaire.setSize(0);
        if(scale.equals("1")){
            questionnaire.setScale(Scale.FIVE);
        }
        else {
            questionnaire.setScale(Scale.TEN);
        }

        questionnaireService.saveQuestionnaire(questionnaire);
        model.addAttribute("questions", questionnaireService.findAllQuestions(questionnaire.getId()));
        model.addAttribute("questionnaireId", questionnaire.getId());
        model.addAttribute("questionNumber", 1);
        return "listOfQQuestions";
    }

    @GetMapping(value = "addQQuestion", params = "changeQuestionnaireFields")
    public String changeFields(@RequestParam("questionnaireId") Long questionnaireId,
                               Model model){
        model.addAttribute("questionnaireId", questionnaireId);
        return "questionnaire";
    }

    @GetMapping(value = "addQQuestion", params = "deleteQuestionnaire")
    public String deleteQuestionnaire(@RequestParam("questionnaireId") Long questionnaireId,
                               Model model){
        questionnaireService.deleteQuestionnaire(questionnaireId);
        return "main";
    }

    @GetMapping(value = "addQQuestion", params = "add")
    public String addQQuestion(@RequestParam("questionnaireId") Long questionnaireId, // add input form in jsp
                               Model model){
        model.addAttribute("questionnaireId", questionnaireId);
        return "listOfQQuestions";
    }

    @GetMapping(value = "addQQuestion", params = "saveQuestionnaire")
    public String saveQuestionnaire(Model model, @RequestParam("questionnaireId") Long questionnaireId){
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
        questionnaire.setSize(questionnaire.getQuestions().size());
        questionnaireService.saveQuestionnaire(questionnaire);
        model.addAttribute("questionnaireId", questionnaireId);
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
        return "listOfQQuestions";
    }
}
