package com.sapiofan.surveys.controllers.questionnaire;

import com.sapiofan.surveys.entities.questionnaire.Description;
import com.sapiofan.surveys.entities.questionnaire.QQuestion;
import com.sapiofan.surveys.entities.questionnaire.Questionnaire;
import com.sapiofan.surveys.entities.questionnaire.Scale;
import com.sapiofan.surveys.services.questionnaire.DescriptionService;
import com.sapiofan.surveys.services.questionnaire.QuestionnaireQuestionsService;
import com.sapiofan.surveys.services.questionnaire.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class QuestionnaireQuestionsController {

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private DescriptionService descriptionService;

    @Autowired
    private QuestionnaireQuestionsService questionnaireQuestionsService;

    @GetMapping(value = "/addQQuestion", params = "changeQuestionnaireFields")
    public String changeFields(@RequestParam("questionnaireId") Long questionnaireId,
                               Model model) {
        model.addAttribute("questionnaireId", questionnaireId);
        return "questionnaire";
    }

    @GetMapping(value = "/addQQuestion", params = "deleteQuestionnaire")
    public String deleteQuestionnaire(@RequestParam("questionnaireId") Long questionnaireId) {
        questionnaireService.deleteQuestionnaire(questionnaireId);
        return "main";
    }

    @GetMapping(value = "/addQQuestion", params = "add")
    public String addQQuestion(@RequestParam("questionnaireId") Long questionnaireId,
                               @RequestParam("question") String inputtedQuestion,
                               Model model) {
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
        QQuestion question = new QQuestion();
        question.setNumber(questionnaire.getQuestions().size() + 1);
        question.setName(inputtedQuestion);
        question.setQuestionnaire(questionnaire);
        questionnaireQuestionsService.saveQQuestion(question);
        model.addAttribute("questionnaireId", questionnaireId);
        model.addAttribute("questions", questionnaireQuestionsService.findAllQuestions(questionnaireId));
        return "questionnaireQuestions";
    }

    @GetMapping(value = "/addQQuestion", params = "addDescriptions")
    public String goToDescriptions(Model model, @RequestParam("questionnaireId") Long questionnaireId) {
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
        questionnaire.setSize(questionnaire.getQuestions().size());
        questionnaireService.saveQuestionnaire(questionnaire);
        int max;
        if (questionnaire.getScale().equals(Scale.FIVE))
            max = 5;
        else
            max = 10;
        model.addAttribute("questionnaireId", questionnaireId);
        List<Description> descriptions = descriptionService.findAllDescriptions(questionnaireId);
        int minimum = 1;
        for (int i = 0; i < descriptions.size(); i++) {
            if (minimum < descriptions.get(i).getEnd_scale())
                minimum = descriptions.get(i).getEnd_scale() + 1;
        }
        model.addAttribute("minimum", minimum);
        model.addAttribute("maximum", max * questionnaire.getQuestions().size());
        model.addAttribute("descriptions", descriptionService.findAllDescriptions(questionnaireId));
        return "descriptions";
    }

    @GetMapping("/deleteQQuestion/{number}")
    public String deleteQQuestionById(@PathVariable("number") Integer number,
                                      @RequestParam("questionnaireId") Long questionnaireId,
                                      Model model) {
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
        QQuestion question = questionnaire.getQuestions().get(number - 1);
        for (int i = 0; i < questionnaire.getQuestions().size() - question.getNumber(); i++) {
            QQuestion question1 = questionnaire.getQuestions().get(number);
            question1.setNumber(question1.getNumber() - 1);
            questionnaireQuestionsService.saveQQuestion(question1);
        }
        questionnaireQuestionsService.deleteQQuestionById(question.getId());
        questionnaireService.saveQuestionnaire(questionnaire);
        model.addAttribute("questionnaireId", questionnaireId);
        model.addAttribute("questions", questionnaireQuestionsService.findAllQuestions(questionnaireId));
        return "questionnaireQuestions";
    }
}
