package com.sapiofan.surveys.controllers;

import com.sapiofan.surveys.entities.Survey;
<<<<<<< HEAD
import com.sapiofan.surveys.services.impl.SurveyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

=======
import com.sapiofan.surveys.entities.User;
import com.sapiofan.surveys.repository.UserRepository;
import com.sapiofan.surveys.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.Instant;
>>>>>>> origin/main
import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
<<<<<<< HEAD
    private SurveyServiceImpl surveyService;




//    @GetMapping(value ={"/", "/login"})
//    public String index(){
//        return "default";
//    }
=======
    private SurveyService surveyService;

    @Autowired
    private UserRepository userRepository;


    @GetMapping
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        Timestamp ts = Timestamp.from(Instant.now());
        user.setCreated_at(ts);

        userRepository.save(user);

        return "register_success";
    }



    @GetMapping(value ={"/", "/login"})
    public String index(){
        return "default";
    }
>>>>>>> origin/main

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
<<<<<<< HEAD
        model.addAttribute("surveyId", 0);
=======
>>>>>>> origin/main
        return "survey";
    }
}
