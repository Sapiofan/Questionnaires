package com.sapiofan.surveys.controllers;

import com.sapiofan.surveys.entities.*;
import com.sapiofan.surveys.services.impl.QuestionnaireServiceImpl;
import com.sapiofan.surveys.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class QuestionnaireController {

    @Autowired
    private QuestionnaireServiceImpl questionnaireService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/createQuestionnaire")
    public String questionnaireForm(){
        return "questionnaire";
    }

    @PostMapping("/createQuestionnaire")
    public String createQuestionnaire(@RequestParam("name") String name,
                                      @RequestParam("description") String description,
                                      @RequestParam("questionnaireId") Long id,
                                      @RequestParam("scale") String scale,
                                      Authentication authentication,
                                      Model model){
        Questionnaire questionnaire;
        if(id == 0) {
            questionnaire = new Questionnaire();
            questionnaire.setName(name);
            questionnaire.setGeneral_description(description);
            questionnaire.setSize(0);
            questionnaire.setUser(getUser(authentication));
            if(scale.equals("1")){
                questionnaire.setScale(Scale.FIVE);
            }
            else {
                questionnaire.setScale(Scale.TEN);
            }
        }
        else{
            questionnaire = questionnaireService.findQuestionnaireById(id);
            questionnaire.setName(name);
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


    private User getUser(Authentication authentication) {
        var nickname = (String) authentication.getPrincipal();
        return userService.findUserByNickname(nickname);
    }
}
