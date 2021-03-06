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


    @PostMapping(value = "/addQuestion", params = "goTo")
    public String addQuestionForm(Model model,
                                  @RequestParam("surveyId") Long surveyId,
                                  @RequestParam("questionId") Long questionId) {
        List<Answer> answers = new ArrayList<>();
        model.addAttribute("answers", answers);
        model.addAttribute("surveyId", surveyId);
        model.addAttribute("questionId", questionId);
        return "addQuestion";
    }

    @PostMapping(value = "/listOfAnswers", params = "add")
    public String addQuestion(@RequestParam("question") String inputtedQuestion,
                              @RequestParam(name = "surveyId") Long surveyId,
                              @RequestParam(name = "questionId") Long questionId,
                              Model model) {
        Question question = surveyQuestionService.createQuestion(questionId, surveyId, inputtedQuestion);
        model.addAttribute("questionId", question.getId());
        model.addAttribute("question", question.getDescription());
        List<Answer> answers = answersService.findAllAnswers(questionId);
        model.addAttribute("answers", answers);
        model.addAttribute("size", answers.size());
        model.addAttribute("input", surveyService.checkInput(answers));
        return "listOfAnswers";
    }

    @PostMapping(value = "/addQuestion", params = "changeQuestionName")
    public String changeQuestionName(Model model,
                                     @RequestParam("questionId") Long questionId) {
        Survey survey = surveyQuestionService.findQuestionById(questionId).getSurvey();
        model.addAttribute("surveyId", survey.getId());
        model.addAttribute("questionId", questionId);
        model.addAttribute("name", surveyQuestionService.findQuestionById(questionId).getDescription());
        return "addQuestion";
    }

    @PostMapping(value = "/changedQuestionNumber", params = "changeQuestionNumber")
    public String changeQuestionNumber(@RequestParam("from") Integer from,
                                       @RequestParam("to") Integer to,
                                       @RequestParam("questionId") Long questionId,
                                       @RequestParam("surveyId") Long surveyId,
                                       Model model) {
        surveyQuestionService.changeQuestionNumber(from, to, surveyId);
        model.addAttribute("surveyId", surveyId);
        model.addAttribute("questionId", questionId);
        List<Question> questions = surveyQuestionService.findAllQuestions(surveyId);
        model.addAttribute("size", questions.size());
        model.addAttribute("questions", questions);
        return "listOfQuestions";
    }

    @GetMapping("/question/{number}")
    public String getQuestionByNumber(@RequestParam("questionId") Long questionId,
                                      Model model) {
        model.addAttribute("questionId", questionId);
        List<Answer> answers = answersService.findAllAnswers(questionId);
        model.addAttribute("answers", answers);
        model.addAttribute("size", answers.size());
        model.addAttribute("input", surveyService.checkInput(answers));
        return "listOfAnswers";
    }

    @PostMapping(value = "/listOfQuestions", params = "addQuestion")
    public String addQuestionWithAnswers(Model model,
                                         @RequestParam("questionId") Long questionId) {
        Question question = surveyQuestionService.findQuestionById(questionId);
        model.addAttribute("questionId", 0);
        model.addAttribute("surveyId", question.getSurvey().getId());
        List<Question> questions = surveyQuestionService.findAllQuestions(question.getSurvey().getId());
        model.addAttribute("questions", questions);
        model.addAttribute("size", questions.size());
        return "listOfQuestions";
    }

    @GetMapping(("/deleteQuestion/{id}"))
    public String deleteQuestionById(@PathVariable("id") Long id,
                                     @RequestParam("surveyId") Long surveyId,
                                     Authentication authentication,
                                     Model model) {
        Survey survey = surveyService.findSurveyById(surveyId);
        if (survey != null) {
            CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
            if (!survey.getUser().getNickname().equals(principal.getUsername())) {
                return "main";
            }
        }
        surveyQuestionService.deleteQuestionByNumber(surveyId, id);
        model.addAttribute("surveyId", surveyId);
        model.addAttribute("questionId", 0);
        model.addAttribute("questions", surveyQuestionService.findAllQuestions(surveyId));
        model.addAttribute("size", surveyQuestionService.findAllQuestions(surveyId).size());
        return "listOfQuestions";
    }
}
