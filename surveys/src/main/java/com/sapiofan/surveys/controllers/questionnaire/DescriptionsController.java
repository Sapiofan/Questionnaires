package com.sapiofan.surveys.controllers.questionnaire;

import com.sapiofan.surveys.entities.questionnaire.Description;
import com.sapiofan.surveys.entities.questionnaire.Questionnaire;
import com.sapiofan.surveys.services.questionnaire.DescriptionService;
import com.sapiofan.surveys.services.questionnaire.QuestionnaireQuestionsService;
import com.sapiofan.surveys.services.questionnaire.QuestionnaireService;
import com.sapiofan.surveys.services.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class DescriptionsController {

    Logger logger = LoggerFactory.getLogger(DescriptionsController.class);

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private UserService userService;

    @Autowired
    private DescriptionService descriptionService;

    @Autowired
    private QuestionnaireQuestionsService questionnaireQuestionsService;

    @GetMapping(value = "/addDescription", params = "addQuestions")
    public String addQuestions(@RequestParam("questionnaireId") Long questionnaireId,
                               Model model) {
        model.addAttribute("questionnaireId", questionnaireId);
        model.addAttribute("questions", questionnaireQuestionsService.findAllQuestions(questionnaireId));
        return "questionnaireQuestions";
    }

    @GetMapping(value = "/addDescription", params = "deleteQuestionnaire")
    public String deleteQuestionnaireFromDescription(@RequestParam("questionnaireId") Long questionnaireId) {
        questionnaireService.deleteQuestionnaire(questionnaireId);
        return "main";
    }

    @GetMapping(value = "/addDescription", params = "addDescription")
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
                model.addAttribute("minimum", minimum);
                model.addAttribute("maximum", maximum);
                model.addAttribute("descriptions", descriptionService.findAllDescriptions(questionnaireId));
                return "descriptions";
            }
        }
        Description description = new Description();
        description.setDescription(inputtedDescription);
        description.setNumber(questionnaire.getDescriptions().size() + 1);
        description.setQuestionnaire(questionnaire);
        description.setStart_scale(minimum);
        description.setEnd_scale(range);
        descriptionService.saveDescription(description);

        model.addAttribute("questionnaireId", questionnaireId);
        model.addAttribute("minimum", range + 1);
        model.addAttribute("maximum", maximum);
        model.addAttribute("descriptions", descriptionService.findAllDescriptions(questionnaireId)
                .stream().sorted(Comparator.comparingInt(Description::getNumber)).collect(Collectors.toList()));
        return "descriptions";
    }

    @GetMapping(value = "/addDescription", params = "saveQuestionnaire")
    public String saveQuestionnaire(Model model) {
        model.addAttribute("questionnaires", questionnaireService.findAllQuestionnaires());
        return "listOfQuestionnaires";
    }

    @GetMapping("/deleteDescription/{id}")
    public String deleteDescriptionById(@PathVariable("id") Long descriptionId,
                                        @RequestParam("questionnaireId") Long questionnaireId,
                                        Model model) {
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
        Description description = descriptionService.findDescriptionById(descriptionId);
        if (description != null) {
            int number = description.getNumber();
            int diff = description.getEnd_scale() - description.getStart_scale() + 1;

            if (number - 2 >= 0 && number < questionnaire.getDescriptions().size()) {
                Description before = questionnaire.getDescriptions().get(number - 2);
                Description after = questionnaire.getDescriptions().get(number);
                after.setStart_scale(after.getStart_scale() - diff / 2);
                descriptionService.saveDescription(before);
                descriptionService.saveDescription(after);
                if ((diff % 2 == 1)) {
                    before.setEnd_scale(before.getEnd_scale() + diff / 2 + 1);
                } else {
                    before.setEnd_scale(before.getEnd_scale() + diff / 2);
                }
            } else if (number - 2 < 0) {
                Description after = questionnaire.getDescriptions().get(number);
                after.setStart_scale(1);
                descriptionService.saveDescription(after);
            } else if (number >= questionnaire.getDescriptions().size()) {
                Description before = questionnaire.getDescriptions().get(number - 2);
                before.setEnd_scale(description.getEnd_scale());
                descriptionService.saveDescription(before);
            }

            for (int i = 0; i < questionnaire.getDescriptions().size() - description.getNumber(); i++) {
                Description description1 = questionnaire.getDescriptions().get(number);
                description1.setNumber(description1.getNumber() - 1);
                descriptionService.saveDescription(description1);
            }
            descriptionService.deleteDescriptionById(description.getId());
            questionnaireService.saveQuestionnaire(questionnaire);
        }

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
                                      Model model) {
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
        List<Description> descriptions = descriptionService.findAllDescriptions(questionnaireId);
        Description description = descriptionService.findDescriptionById(descriptionId);
        int number = description.getNumber();
        int minimum, max;

        if (number - 1 > 0 && number + 1 == descriptions.size()) {
            Description before = descriptionService.findDescriptionByNumber(questionnaireId, number-1);
            Description after = descriptionService.findDescriptionByNumber(questionnaireId, number+1);
            minimum = before.getStart_scale() + 1;
            max = after.getEnd_scale() - 1;
        } else if (number - 1 <= 0) {
            Description after = descriptionService.findDescriptionByNumber(questionnaireId, number+1);
            minimum = 1;
            max = after.getEnd_scale() - 1;
        } else if (number + 1 > descriptions.size()) {
            Description before = descriptionService.findDescriptionByNumber(questionnaireId, number-1);
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

    @PostMapping(value = "/addDescription", params = "back")
    public String backToDescriptions(@RequestParam("questionnaireId") Long questionnaireId,
                                     Model model) {
        List<Description> descriptions = descriptionService.findAllDescriptions(questionnaireId);
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
        descriptions = descriptions
                .stream().sorted(Comparator.comparingInt(Description::getNumber)).collect(Collectors.toList());
        model.addAttribute("questionnaireId", questionnaireId);
        model.addAttribute("minimum", descriptionService.minimum(descriptions));
        model.addAttribute("maximum", questionnaireService.maximum(questionnaire));
        model.addAttribute("descriptions", descriptions);
        return "descriptions";
    }

    @PostMapping(value = "/addDescription", params = "update")
    public String editDescription(@RequestParam("questionnaireId") Long questionnaireId,
                                  @RequestParam("description") String inputtedDescription,
                                  @RequestParam("range1") Integer rangeLow,
                                  @RequestParam("range2") Integer rangeHigh,
                                  @RequestParam("descriptionId") Long descriptionId,
                                  Model model) {
        Description description = descriptionService.findDescriptionById(descriptionId);
        description.setDescription(inputtedDescription);
        description.setStart_scale(rangeLow);
        description.setEnd_scale(rangeHigh);
        descriptionService.saveDescription(description);
        description = descriptionService.findDescriptionById(descriptionId);
        int number = description.getNumber();
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
        if (number - 1 > 0) {
            Description before = descriptionService.findDescriptionByNumber(questionnaireId, number-1);
            before.setEnd_scale(rangeLow - 1);
            descriptionService.saveDescription(before);
        }
        if (number < questionnaire.getDescriptions().size()) {
            Description after = descriptionService.findDescriptionByNumber(questionnaireId, number+1);
            after.setStart_scale(rangeHigh + 1);
            descriptionService.saveDescription(after);
        }
        List<Description> descriptions = descriptionService.findAllDescriptions(questionnaireId);
        descriptions = descriptions
                .stream().sorted(Comparator.comparingInt(Description::getNumber)).collect(Collectors.toList());
        model.addAttribute("questionnaireId", questionnaireId);
        model.addAttribute("minimum", descriptionService.minimum(descriptions));
        model.addAttribute("maximum", questionnaireService.maximum(questionnaire));
        model.addAttribute("descriptions", descriptions);
        return "descriptions";
    }
}
