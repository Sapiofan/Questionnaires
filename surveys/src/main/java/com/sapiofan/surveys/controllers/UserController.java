package com.sapiofan.surveys.controllers;

import com.sapiofan.surveys.entities.Survey;
import com.sapiofan.surveys.services.impl.SurveyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    private SurveyServiceImpl surveyService;




//    @GetMapping(value ={"/", "/login"})
//    public String index(){
//        return "default";
//    }

    @GetMapping("/main")
    public String main(
            @RequestParam(required = false, defaultValue = "World") String name,
            Model model
    ) {
        model.addAttribute("name", name);
        return "main";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Survey> surveys = surveyService.findAllSurveys();
        model.addAttribute("surveys",surveys);
        return "list";
    }

    @GetMapping("/questionnaire")
    public String createQuestionnaire() {
        return "questionnaire";
    }

    @GetMapping("/survey")
    public String creating(Model model) {
        model.addAttribute("surveyId", 0);
        return "survey";
    }
}
