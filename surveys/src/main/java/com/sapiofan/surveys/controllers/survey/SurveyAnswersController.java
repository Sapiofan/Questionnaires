package com.sapiofan.surveys.controllers.survey;

import com.sapiofan.surveys.entities.survey.Answer;
import com.sapiofan.surveys.entities.survey.Question;
import com.sapiofan.surveys.entities.survey.Survey;
import com.sapiofan.surveys.security.realization.CustomUserDetails;
import com.sapiofan.surveys.services.survey.AnswersService;
import com.sapiofan.surveys.services.survey.SurveyQuestionService;
import com.sapiofan.surveys.services.survey.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SurveyAnswersController {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private AnswersService answersService;

    @Autowired
    private SurveyQuestionService surveyQuestionService;


    @GetMapping("/listOfAnswers")
    public String showListAnswers(@RequestParam("questionId") Long questionId, Model model) {
        model.addAttribute("questionId", questionId);
        model.addAttribute("question", surveyQuestionService.findQuestionById(questionId).getDescription());
        List<Answer> answers = answersService.findAllAnswers(questionId);
        model.addAttribute("answers", answers);
        model.addAttribute("size", answers.size());
        model.addAttribute("input", false);
        return "listOfAnswers";
    }

    @GetMapping("/listOfAnswers/{number}")
    public String showAnswers(@PathVariable("number") Integer number,
                              @RequestParam("surveyId") Long surveyId,
                              Model model) {
        Survey survey = surveyService.findSurveyById(surveyId);
        Long questionId = 1l;
        if(number > 0 && number <= survey.getQuestions().size()) {
            questionId = survey.getQuestions().get(number - 1).getId();
        }
        model.addAttribute("questionId", questionId);
        model.addAttribute("question", surveyQuestionService.findQuestionById(questionId).getDescription());
        List<Answer> answers = answersService.findAllAnswers(questionId);
        model.addAttribute("answers", answers);
        model.addAttribute("size", answers.size());
        model.addAttribute("input", surveyService.checkInput(answers));
        return "listOfAnswers";
    }

    @PostMapping(value = "/listOfAnswers", params = "saveAnswer")
    public String addAnswer(Model model,
                            @RequestParam("questionId") Long questionId,
                            @RequestParam("answer") String inputtedAnswer,
                            @RequestParam("correctAnswer") String correctness) {
        Question question = surveyQuestionService.findQuestionById(questionId);
        answersService.createAnswer(question, inputtedAnswer, correctness);
        model.addAttribute("questionId", questionId);
        model.addAttribute("question", question.getDescription());
        List<Answer> newAnswers = answersService.findAllAnswers(questionId);
        model.addAttribute("answers", newAnswers);
        model.addAttribute("size", newAnswers.size());
        model.addAttribute("input", surveyService.checkInput(newAnswers));
        return "listOfAnswers";
    }

    @GetMapping(("/deleteAnswer/{id}"))
    public String deleteAnswerByNumber(@PathVariable("id") Long answerId,
                                   @RequestParam("questionId") Long questionId,
                                   Authentication authentication,
                                   Model model) {
        Question question = surveyQuestionService.findQuestionById(questionId);
        Survey survey = question.getSurvey();
        if(survey != null){
            CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
            if(!survey.getUser().getNickname().equals(principal.getUsername())){
                return "main";
            }
        }
        answersService.deleteAnswerByNumber(questionId, answerId);
        model.addAttribute("questionId", questionId);
        model.addAttribute("question", question.getDescription());
        model.addAttribute("answers", answersService.findAllAnswers(questionId));
        model.addAttribute("size", answersService.findAllAnswers(questionId).size());
        model.addAttribute("input", surveyService.checkInput(answersService.findAllAnswers(questionId)));
        return "listOfAnswers";
    }

    @GetMapping(("/editAnswer/{number}"))
    public String editAnswer(@PathVariable("number") Integer number,
                             @RequestParam("questionId") Long questionId,
                             Authentication authentication,
                             Model model) {
        Survey survey = surveyQuestionService.findQuestionById(questionId).getSurvey();
        if(survey != null){
            CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
            if(!survey.getUser().getNickname().equals(principal.getUsername())){
                return "main";
            }
        }
        Answer answer = answersService.findAnswerByNumber(questionId, number);
        model.addAttribute("answerId", answer.getId());
        model.addAttribute("questionId", questionId);
        model.addAttribute("answer", answer);
        model.addAttribute("value", getValue(answer));
        model.addAttribute("size", answersService.findAllAnswers(questionId).size());
        return "updateAnswer";
    }

    @PostMapping(value = "/listOfAnswers", params = "updateAnswer")
    public String updateAnswer(Model model,
                               @RequestParam("questionId") Long questionId,
                               @RequestParam("answerId") Long answerId,
                               @RequestParam("number") Integer number,
                               @RequestParam("answer") String inputtedAnswer,
                               @RequestParam("correctAnswer") String correctness) {
        Question question = surveyQuestionService.findQuestionById(questionId);
        answersService.updateAnswer(answerId, inputtedAnswer, correctness, number, questionId);
        List<Answer> answers = answersService.findAllAnswers(questionId);
        model.addAttribute("questionId", questionId);
        model.addAttribute("question", question.getDescription());
        model.addAttribute("answers", answers);
        model.addAttribute("size", answers.size());
        model.addAttribute("input", surveyService.checkInput(answers));
        return "listOfAnswers";
    }

    @PostMapping(value = "/listOfAnswers", params = "backToAnswersList")
    public String backToAnswersList(@RequestParam("questionId") Long questionId,
                                    Model model,
                                    Authentication authentication) {
        model.addAttribute("questionId", questionId);
        model.addAttribute("question", surveyQuestionService.findQuestionById(questionId).getDescription());
        List<Answer> answers = answersService.findAllAnswers(questionId);
        model.addAttribute("answers", answers);
        model.addAttribute("size", answers.size());
        model.addAttribute("input", surveyService.checkInput(answers));
        return "listOfAnswers";
    }

    private int getValue(Answer answer){
        if(answer.getCorrectness())
            return 1;
        else
            return 2;
    }
}
