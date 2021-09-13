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
import org.springframework.web.bind.annotation.PostMapping;
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
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
        model.addAttribute("value", getValue(questionnaire));
        model.addAttribute("name", questionnaire.getName());
        model.addAttribute("description", questionnaire.getGeneral_description());
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
        questionnaireQuestionsService.createQQuestion(questionnaireId, inputtedQuestion);
        model.addAttribute("questionnaireId", questionnaireId);
        List<QQuestion> qQuestions = questionnaireQuestionsService.findAllQuestions(questionnaireId);
        model.addAttribute("questions", qQuestions);
        model.addAttribute("size", qQuestions.size());
        return "questionnaireQuestions";
    }

    @GetMapping(value = "/addQQuestion", params = "addDescriptions")
    public String goToDescriptions(Model model, @RequestParam("questionnaireId") Long questionnaireId) {
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
        questionnaire.setSize(questionnaire.getQuestions().size());
        questionnaireService.saveQuestionnaire(questionnaire);
        int max = defineMax(questionnaire);
        model.addAttribute("questionnaireId", questionnaireId);
        List<Description> descriptions = descriptionService.findAllDescriptions(questionnaireId);
        int minimum = defineMin(descriptions);
        model.addAttribute("minimum", minimum);
        model.addAttribute("maximum", max * questionnaire.getQuestions().size());
        model.addAttribute("descriptions", descriptions);
        return "descriptions";
    }

    @PostMapping(value = "/changedNumber", params = "changeQuestionNumber")
    public String changeQuestionNumber(@RequestParam("from") Integer from,
                                       @RequestParam("to") Integer to,
                                       @RequestParam("questionnaireId") Long questionnaireId,
                                       Model model){
        questionnaireQuestionsService.changeQuestionNumber(from, to, questionnaireId);
        model.addAttribute("questionnaireId", questionnaireId);
        List<QQuestion> qQuestions = questionnaireQuestionsService.findAllQuestions(questionnaireId);
        model.addAttribute("questions", qQuestions);
        model.addAttribute("size", qQuestions.size());
        return "questionnaireQuestions";
    }

    @GetMapping("/deleteQQuestion/{id}")
    public String deleteQQuestionById(@PathVariable("id") Long questionId,
                                      @RequestParam("questionnaireId") Long questionnaireId,
                                      Model model) {
        questionnaireQuestionsService.deleteQQuestionById(questionnaireId, questionId);
        model.addAttribute("questionnaireId", questionnaireId);
        model.addAttribute("questions", questionnaireQuestionsService.findAllQuestions(questionnaireId));
        return "questionnaireQuestions";
    }

    private int defineMax(Questionnaire questionnaire) {
        int max;
        if (questionnaire.getScale().equals(Scale.FIVE))
            max = 5;
        else
            max = 10;
        return max;
    }

    private int defineMin(List<Description> descriptions) {
        int minimum = 1;
        for (int i = 0; i < descriptions.size(); i++) {
            if (minimum < descriptions.get(i).getEnd_scale())
                minimum = descriptions.get(i).getEnd_scale() + 1;
        }
        return minimum;
    }

    private int getValue(Questionnaire questionnaire){
        if(questionnaire.getScale().equals(Scale.FIVE))
            return 5;
        else
            return 10;
    }
}
