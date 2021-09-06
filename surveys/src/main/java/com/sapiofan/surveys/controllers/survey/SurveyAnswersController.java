package com.sapiofan.surveys.controllers.survey;

import com.sapiofan.surveys.entities.survey.Answer;
import com.sapiofan.surveys.entities.survey.Question;
import com.sapiofan.surveys.services.survey.AnswersService;
import com.sapiofan.surveys.services.survey.SurveyQuestionService;
import com.sapiofan.surveys.services.survey.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class SurveyAnswersController {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private AnswersService answersService;

    @Autowired
    private SurveyQuestionService surveyQuestionService;


    @GetMapping(value = "/addAnswer", params = "addAnswer")
    public String listOfAnswers(Model model,
                                @RequestParam("questionId") String questionId) {
        model.addAttribute("questionId", questionId);
        return "addAnswer";
    }

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
        Long questionId = surveyService.findSurveyById(surveyId).getQuestions().get(number - 1).getId();
        model.addAttribute("questionId", questionId);
        model.addAttribute("question", surveyQuestionService.findQuestionById(questionId).getDescription());
        List<Answer> answers = answersService.findAllAnswers(questionId);
        model.addAttribute("answers", answers);
        model.addAttribute("size", answers.size());
        model.addAttribute("input", surveyService.checkInput(answers));
        return "listOfAnswers";
    }

    @PostMapping(name = "/listOfAnswers", params = "backToAnswers")
    public String backToAnswers(@RequestParam("questionId") Long questionId, Model model) {
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
        List<Answer> answers = answersService.findAllAnswers(questionId);
        boolean flag = false;
        for (int i = 0; i < answers.size(); i++) {
            if (answers.get(i).getAnswer().equals(inputtedAnswer)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            Answer answer = new Answer(question.getAnswers().size() + 1, question, inputtedAnswer, correctness.equals("1"));
            surveyQuestionService.saveQuestion(question);
            answersService.saveAnswer(answer);
        }
        model.addAttribute("questionId", questionId);
        model.addAttribute("question", question.getDescription());
        List<Answer> newAnswers = answersService.findAllAnswers(questionId);
        model.addAttribute("answers", newAnswers);
        model.addAttribute("size", newAnswers.size());
        model.addAttribute("input", surveyService.checkInput(newAnswers));
        return "listOfAnswers";
    }

    @GetMapping(("/deleteAnswer/{number}"))
    public String deleteAnswerByNumber(@PathVariable("number") Integer number,
                                   @RequestParam("questionId") Long questionId,
                                   Model model) {
        Question question = surveyQuestionService.findQuestionById(questionId);
        answersService.deleteAnswerByNumber(questionId, number);
        model.addAttribute("questionId", questionId);
        model.addAttribute("question", question.getDescription());
        model.addAttribute("answers", answersService.findAllAnswers(questionId)
                .stream()
                .sorted(Comparator.comparingInt(Answer::getNumber))
                .collect(Collectors.toList()));
        model.addAttribute("size", answersService.findAllAnswers(questionId).size());
        model.addAttribute("input", surveyService.checkInput(answersService.findAllAnswers(questionId)));
        return "listOfAnswers";
    }

    @GetMapping(("/editAnswer/{number}"))
    public String editAnswer(@PathVariable("number") Integer number,
                             @RequestParam("questionId") Long questionId,
                             Model model) {
        Answer answer = answersService.findAnswerByNumber(questionId, number);
        model.addAttribute("answerId", answer.getId());
        model.addAttribute("questionId", questionId);
        model.addAttribute("answer", answer);
        return "updateAnswer";
    }

    @PostMapping(value = "/updateAnswer", params = "saveAnswer")
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
        model.addAttribute("answers", answers
                .stream()
                .sorted(Comparator.comparingInt(Answer::getNumber))
                .collect(Collectors.toList()));
        model.addAttribute("size", answers.size());
        model.addAttribute("input", surveyService.checkInput(answers));
        return "listOfAnswers";
    }

    @PostMapping(value = "/updateAnswer", params = "backToAnswersList")
    public String backToAnswersList(@RequestParam("questionId") Long questionId,
                                    Model model) {
        model.addAttribute("questionId", questionId);
        model.addAttribute("question", surveyQuestionService.findQuestionById(questionId).getDescription());
        List<Answer> answers = answersService.findAllAnswers(questionId);
        model.addAttribute("answers", answers);
        model.addAttribute("size", answers.size());
        model.addAttribute("input", surveyService.checkInput(answers));
        return "listOfAnswers";
    }


}