package com.sapiofan.surveys.controllers;

import com.sapiofan.surveys.entities.Survey;
import com.sapiofan.surveys.entities.User;
import com.sapiofan.surveys.repository.UserRepository;
import com.sapiofan.surveys.security.realization.CustomUserDetailsService;
import com.sapiofan.surveys.services.impl.SurveyServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;

@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private SurveyServiceImpl surveyService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService userService;


    @GetMapping
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("exist", false);
        return "registration";
    }

    @GetMapping(value = "/process", params = "signUp")
    public String processRegister(@RequestParam("nickname") String nickname,
                                  @RequestParam("password") String password, Model model) {
        if(userService.checkIfUserExists(nickname)){
            model.addAttribute("exist", true);
            return "registration";
        }
        User user = new User();
        user.setPassword(password);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setNickname(nickname);
        user.setPassword(encodedPassword);
        Timestamp ts = Timestamp.from(Instant.now());
        user.setCreated_at(ts);

        userRepository.save(user);

        return "signup_success";
    }

    @GetMapping("/main")
    public String main(
    ) {
        return "main";
    }

    @GetMapping("/logout")
    public String signOut(){
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:/index";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Survey> surveys = surveyService.findAllSurveys();
        surveys.stream().sorted(Comparator.comparingLong(Survey::getId));
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
