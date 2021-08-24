package com.sapiofan.surveys.controllers;

import com.sapiofan.surveys.entities.Question;
import com.sapiofan.surveys.entities.Survey;
import com.sapiofan.surveys.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/survey")
public class SurveyController {
    @Autowired
    public SurveyService surveyService;
    private static int counter = 0;

    @PostMapping("/processSurvey")
    public String saveSurvey(){
        return "main";
    }

//    @GetMapping("/addQuestion")
//    public String addQuestion(){
//        return "addQuestion";
//    }

    @PostMapping("/addQuestion")
    public String addQuestion(@ModelAttribute("question") Question question){

        return "redirect:/survey";
    }

    @GetMapping("/creating")
    public String creating(Model model) {
        model.addAttribute("questions", surveyService.findAllQuestions(1l));
        return "ListQuestions";
    }

    @PostMapping("/creating")
    public String addSurvey(@RequestParam String name, Model model){
        Survey survey = new Survey();
        survey.setName(name);
        surveyService.save(survey);
        model.addAttribute("questions", surveyService.findAllQuestions(1l));
        return "ListQuestions";
    }




    @PostMapping()
    public String addAnswer(Model model){
        model.addAttribute("number", onClick());
        return "addAnswer";
    }

    public int onClick(){
        return ++counter;
    }
}
