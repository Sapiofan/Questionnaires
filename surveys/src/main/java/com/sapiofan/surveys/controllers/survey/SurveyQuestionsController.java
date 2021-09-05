package com.sapiofan.surveys.controllers.survey;

import com.sapiofan.surveys.entities.survey.Answer;
import com.sapiofan.surveys.entities.survey.Question;
import com.sapiofan.surveys.entities.survey.Survey;
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

import java.util.ArrayList;
import java.util.List;

@Controller
public class SurveyQuestionsController {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private SurveyQuestionService surveyQuestionService;

    @Autowired
    private AnswersService answersService;

    @GetMapping(value = "/addQuestion", params = "add")
    public String addQuestionForm(Model model,
                                  @RequestParam("surveyId") Long surveyId,
                                  @RequestParam("questionId") Long questionId) {
        List<Answer> answers = new ArrayList<>();
        model.addAttribute("answers", answers);
        model.addAttribute("surveyId", surveyId);
        model.addAttribute("questionId", questionId);
        return "addQuestion";
    }

    @PostMapping(value = "/addQuestion", params = "add")
    public String addQuestion(@RequestParam("question") String inputtedQuestion,
                              @RequestParam(name = "surveyId") Long surveyId,
                              @RequestParam(name = "questionId") Long questionId,
                              Model model) {
        Question question;
        if (questionId == 0) {
            Survey survey = surveyService.findSurveyById(surveyId);
            question = new Question(survey.getQuestions().size() + 1, inputtedQuestion, survey);
        } else {
            question = surveyQuestionService.findQuestionById(questionId);
            question.setDescription(inputtedQuestion);
        }
        surveyQuestionService.saveQuestion(question);
        model.addAttribute("questionId", question.getId());
        model.addAttribute("question", question.getDescription());
        List<Answer> answers = answersService.findAllAnswers(questionId);
        model.addAttribute("answers", answers);
        model.addAttribute("size", answers.size());
        model.addAttribute("input", surveyService.checkInput(answers));
        return "listOfAnswers";
    }

    @GetMapping(value = "/addAnswer", params = "changeQuestionName")
    public String changeQuestionName(Model model,
                                     @RequestParam("questionId") Long questionId) {
        model.addAttribute("surveyId", surveyQuestionService.findQuestionById(questionId).getSurvey().getId());
        model.addAttribute("questionId", questionId);
        return "addQuestion";
    }

    @GetMapping("/question/{number}")
    public String getQuestionByNumber(@PathVariable("number") Integer number,
                                      @RequestParam("questionId") Long questionId,
                                      Model model) {
        model.addAttribute("questionId", questionId);
        List<Answer> answers = answersService.findAllAnswers(questionId);
        model.addAttribute("answers", answers);
        model.addAttribute("size", answers.size());
        model.addAttribute("input", surveyService.checkInput(answers));
        return "listOfAnswers";
    }

    @GetMapping(value = "/addAnswer", params = "addQuestion")
    public String addQuestionWithAnswers(Model model,
                                         @RequestParam("questionId") Long questionId) {
        Question question = surveyQuestionService.findQuestionById(questionId);
        model.addAttribute("questionId", 0);
        model.addAttribute("surveyId", question.getSurvey().getId());
        List<Question> questions = surveyQuestionService.findAllQuestions(question.getSurvey().getId());
        model.addAttribute("questions", questions);
        return "listOfQuestions";
    }

    @GetMapping(("/deleteQuestion/{number}"))
    public String deleteQuestionById(@PathVariable("number") Integer number,
                                     @RequestParam("surveyId") Long surveyId,
                                     Model model) {
        Survey survey = surveyService.findSurveyById(surveyId);
        Question question = survey.getQuestions().get(number - 1);
        for (int i = 0; i < surveyQuestionService.findAllQuestions(surveyId).size() - question.getNumber(); i++) {
            Question question1 = survey.getQuestions().get(number);
            question1.setNumber(question1.getNumber() - 1);
            surveyQuestionService.saveQuestion(question1);
        }
        surveyQuestionService.deleteQuestionById(question.getId());
        surveyService.save(survey);
        model.addAttribute("surveyId", surveyId);
        model.addAttribute("questionId", 0);
        model.addAttribute("questions", surveyQuestionService.findAllQuestions(surveyId));
        return "listOfQuestions";
    }
}
