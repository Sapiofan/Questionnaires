package com.sapiofan.surveys.controllers;

import com.sapiofan.surveys.entities.Question;
import com.sapiofan.surveys.services.SurveyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/survey")
public class SurveyController {
    public SurveyService surveyService;

    @PostMapping("/processSurvey")
    public String saveSurvey(@RequestParam("question") String question,
                             @RequestParam("answer") String answer){

        return "";
    }

//    @GetMapping("/addQuestion")
//    public String addQuestion(){
//        return "addQuestion";
//    }

    @PostMapping("/addQuestion")
    public String addQuestion(@ModelAttribute("question") Question question){
        surveyService.addQuestion();
        return "";
    }

    @PostMapping()
    public String addAnswer(){

        return "";
    }
}
