package com.sapiofan.surveys.controllers.user;

import com.sapiofan.surveys.entities.questionnaire.Questionnaire;
import com.sapiofan.surveys.entities.survey.Survey;
import com.sapiofan.surveys.security.realization.CustomUserDetailsService;
import com.sapiofan.surveys.services.questionnaire.impl.QuestionnaireServiceImpl;
import com.sapiofan.surveys.services.survey.SurveyService;
import com.sapiofan.surveys.services.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailsService customUserService;

    @Autowired
    private QuestionnaireServiceImpl questionnaireService;


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
        if (customUserService.checkIfUserExists(nickname)) {
            model.addAttribute("exist", true);
            return "registration";
        }
        userService.save(nickname, password);
        return "signup_success";
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/help")
    public String help() {
        return "help";
    }

    @GetMapping("/logout")
    public String signOut() {
        SecurityContextHolder.getContext().setAuthentication(null);
        return "redirect:/index";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Survey> surveys = surveyService.findAllSurveys();
        model.addAttribute("surveys", surveys);
        return "list";
    }

    @PostMapping(value = "/search", params = "searchRows")
    public String search(@RequestParam("type") String type,
                         @RequestParam("search") String search,
                         Model model){
        if(type.equals("survey")){
            model.addAttribute("surveys", surveyService.findBySurveyName(search));
        }
        else {
            model.addAttribute("surveys", surveyService.findSurveyByNickName(search));
        }
        return "list";
    }

    @PostMapping(value = "/search", params = "backToList")
    public String backToList(Model model){
        List<Survey> surveys = surveyService.findAllSurveys();
        model.addAttribute("surveys", surveys);
        return "list";
    }

    @GetMapping("/listOfQuestionnaires")
    public String listOfQuestionnaires(Model model) {
        List<Questionnaire> questionnaires = questionnaireService.findAllQuestionnaires();
        model.addAttribute("questionnaires", questionnaires);
        return "listOfQuestionnaires";
    }

    @PostMapping(value = "/searchQuestionnaire", params = "searchRows")
    public String searchQuestionnaires(@RequestParam("type") String type,
                         @RequestParam("search") String search,
                         Model model){
        if(type.equals("questionnaire")){
            model.addAttribute("questionnaires", questionnaireService.findByQuestionnaireName(search));
        }
        else {
            model.addAttribute("questionnaires", questionnaireService.findQuestionnaireByNickName(search));
        }
        return "listOfQuestionnaires";
    }

    @PostMapping(value = "/searchQuestionnaire", params = "backToList")
    public String backToQuestionnaireList(Model model){
        List<Questionnaire> questionnaires = questionnaireService.findAllQuestionnaires();
        model.addAttribute("questionnaires", questionnaires);
        return "listOfQuestionnaires";
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
