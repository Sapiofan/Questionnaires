package com.sapiofan.surveys.controllers.questionnaire;

import com.sapiofan.surveys.entities.questionnaire.Description;
import com.sapiofan.surveys.entities.questionnaire.Questionnaire;
import com.sapiofan.surveys.security.realization.CustomUserDetails;
import com.sapiofan.surveys.services.questionnaire.DescriptionService;
import com.sapiofan.surveys.services.questionnaire.QuestionnaireQuestionsService;
import com.sapiofan.surveys.services.questionnaire.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
public class DescriptionsController {

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private DescriptionService descriptionService;

    @Autowired
    private QuestionnaireQuestionsService questionnaireQuestionsService;

    @PostMapping(value = "/questionnaireQuestions", params = "addQuestions")
    public String addQuestions(@RequestParam("questionnaireId") Long questionnaireId,
                               Model model) {
        model.addAttribute("questionnaireId", questionnaireId);
        model.addAttribute("questions", questionnaireQuestionsService.findAllQuestions(questionnaireId));
        return "questionnaireQuestions";
    }

    @PostMapping(value = "/main", params = "deleteQuestionnaireD")
    public String deleteQuestionnaireFromDescription(@RequestParam("questionnaireId") Long questionnaireId) {
        questionnaireService.deleteQuestionnaire(questionnaireId);
        return "main";
    }

    @PostMapping(value = "/descriptions", params = "addDescription")
    public String addDescription(@RequestParam("questionnaireId") Long questionnaireId,
                                 @RequestParam("description") String inputtedDescription,
                                 @RequestParam("range") Integer range,
                                 @RequestParam("minimum") Integer minimum,
                                 @RequestParam("maximum") Integer maximum,
                                 Model model
    ) {
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
        List<Description> descriptionList = questionnaire.getDescriptions();

        for (Description value : descriptionList) {
            if (value.getDescription().equals(inputtedDescription)) {
                model.addAttribute("questionnaireId", questionnaireId);
                model.addAttribute("minimum", descriptionService.minimum(descriptionList));
                model.addAttribute("maximum", maximum);
                model.addAttribute("descriptions", descriptionService.findAllDescriptions(questionnaireId));
                return "descriptions";
            }
        }

        descriptionService.createDescription(inputtedDescription, questionnaire, minimum, range);

        model.addAttribute("questionnaireId", questionnaireId);
        model.addAttribute("minimum", range + 1);
        model.addAttribute("maximum", maximum);
        model.addAttribute("descriptions", descriptionService.findAllDescriptions(questionnaireId)
                .stream().sorted(Comparator.comparingInt(Description::getNumber)).collect(Collectors.toList()));
        return "descriptions";
    }

    @PostMapping(value = "/listOfQuestionnaires", params = "saveQuestionnaire")
    public String saveQuestionnaire(Model model) {
        model.addAttribute("questionnaires", questionnaireService.findAllQuestionnaires());
        return "listOfQuestionnaires";
    }

    @GetMapping("/deleteDescription/{id}")
    public String deleteDescriptionById(@PathVariable("id") Long descriptionId,
                                        @RequestParam("questionnaireId") Long questionnaireId,
                                        Authentication authentication,
                                        Model model) {
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
        if(questionnaire != null){
            CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
            if(!questionnaire.getUser().getNickname().equals(principal.getUsername())){
                return "main";
            }
        }
        descriptionService.deleteDescriptionById(descriptionId, questionnaire);
        List<Description> descriptions = descriptionService.findAllDescriptions(questionnaireId);
        int minimum = descriptionService.minimum(descriptions);
        int max = questionnaireService.maximum(questionnaire);
        model.addAttribute("questionnaireId", questionnaireId);
        model.addAttribute("descriptions", descriptionService.findAllDescriptions(questionnaireId));
        model.addAttribute("minimum", minimum);
        model.addAttribute("maximum", max);
        return "descriptions";
    }

    @GetMapping("/editDescription/{id}")
    public String editDescriptionForm(@PathVariable("id") Long descriptionId,
                                      @RequestParam("questionnaireId") Long questionnaireId,
                                      Authentication authentication,
                                      Model model) {
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
        if(questionnaire != null){
            CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
            if(!questionnaire.getUser().getNickname().equals(principal.getUsername())){
                return "main";
            }
        }
        List<Description> descriptions = descriptionService.findAllDescriptions(questionnaireId);
        Description description = descriptionService.findDescriptionById(descriptionId);
        int number = description.getNumber();
        int minimum, max;

        if (number - 1 > 0 && number + 1 <= descriptions.size()) {
            Description before = descriptionService.findDescriptionByNumber(questionnaireId, number - 1);
            Description after = descriptionService.findDescriptionByNumber(questionnaireId, number + 1);
            minimum = before.getStart_scale() + 1;
            max = after.getEnd_scale() - 1;
        } else if (number - 1 <= 0 && number + 1 <= descriptions.size()) {
            Description after = descriptionService.findDescriptionByNumber(questionnaireId, number + 1);
            minimum = 1;
            max = after.getEnd_scale() - 1;
        } else if (number + 1 > descriptions.size() && number - 1 > 0) {
            Description before = descriptionService.findDescriptionByNumber(questionnaireId, number - 1);
            minimum = before.getStart_scale() + 1;
            max = questionnaireService.maximum(questionnaire);
        } else {
            minimum = 1;
            max = questionnaireService.maximum(questionnaire);
        }
        model.addAttribute("descriptionId", descriptionId);
        model.addAttribute("questionnaireId", questionnaireId);
        model.addAttribute("minimum", minimum);
        model.addAttribute("maximum", max);
        model.addAttribute("value", description.getEnd_scale());
        model.addAttribute("value_min", description.getStart_scale());
        model.addAttribute("description", description);
        return "editDescription";
    }

    @PostMapping(value = "/descriptions", params = "back")
    public String backToDescriptions(@RequestParam("questionnaireId") Long questionnaireId,
                                     Model model) {
        List<Description> descriptions = descriptionService.findAllDescriptions(questionnaireId);
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
        model.addAttribute("questionnaireId", questionnaireId);
        model.addAttribute("minimum", descriptionService.minimum(descriptions));
        model.addAttribute("maximum", questionnaireService.maximum(questionnaire));
        model.addAttribute("descriptions", descriptions);
        return "descriptions";
    }

    @PostMapping(value = "/descriptions", params = "update")
    public String editDescription(@RequestParam("questionnaireId") Long questionnaireId,
                                  @RequestParam("description") String inputtedDescription,
                                  @RequestParam("range1") Integer rangeLow,
                                  @RequestParam("range2") Integer rangeHigh,
                                  @RequestParam("descriptionId") Long descriptionId,
                                  Model model) {
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
        descriptionService.updateDescription(descriptionId, inputtedDescription, rangeLow, rangeHigh, questionnaire);
        List<Description> descriptions = descriptionService.findAllDescriptions(questionnaireId);
        model.addAttribute("questionnaireId", questionnaireId);
        model.addAttribute("minimum", descriptionService.minimum(descriptions));
        model.addAttribute("maximum", questionnaireService.maximum(questionnaire));
        model.addAttribute("descriptions", descriptions);
        return "descriptions";
    }
}
