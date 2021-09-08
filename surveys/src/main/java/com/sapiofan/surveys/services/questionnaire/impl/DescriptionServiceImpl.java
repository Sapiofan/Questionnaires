package com.sapiofan.surveys.services.questionnaire.impl;

import com.sapiofan.surveys.entities.questionnaire.Description;
import com.sapiofan.surveys.entities.questionnaire.Questionnaire;
import com.sapiofan.surveys.repository.questionnaire.DescriptionRepository;
import com.sapiofan.surveys.services.questionnaire.DescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DescriptionServiceImpl implements DescriptionService {

    @Autowired
    private DescriptionRepository descriptionRepository;

    @Transactional
    public List<Description> findAllDescriptions(Long id) {
        return descriptionRepository.findAllDescriptions(id);
    }

    @Transactional
    public Description findDescriptionById(Long id) {
        return descriptionRepository.findDescriptionById(id);
    }

    @Transactional
    public Description findDescriptionByNumber(Long questionnaireId, Integer number) {
        return descriptionRepository.findDescriptionByNumber(questionnaireId, number);
    }

    @Transactional
    public void saveDescription(Description description) {
        descriptionRepository.save(description);
    }

    @Transactional
    public Description createDescription(String inputtedDescription, Questionnaire questionnaire,
                                         Integer minimum, Integer range) {
        Description description = new Description();
        description.setDescription(inputtedDescription);
        description.setNumber(questionnaire.getDescriptions().size() + 1);
        description.setQuestionnaire(questionnaire);
        description.setStart_scale(minimum);
        description.setEnd_scale(range);
        saveDescription(description);
        return description;
    }

    @Transactional
    public Description updateDescription(Long descriptionId, String inputtedDescription,
                                         Integer rangeLow, Integer rangeHigh, Questionnaire questionnaire) {
        Description description = findDescriptionById(descriptionId);
        description.setDescription(inputtedDescription);
        description.setStart_scale(rangeLow);
        description.setEnd_scale(rangeHigh);
        saveDescription(description);
        description = findDescriptionById(descriptionId);
        int number = description.getNumber();
        if (number - 1 > 0) {
            Description before = findDescriptionByNumber(questionnaire.getId(), number - 1);
            before.setEnd_scale(rangeLow - 1);
            saveDescription(before);
        }
        if (number < questionnaire.getDescriptions().size()) {
            Description after = findDescriptionByNumber(questionnaire.getId(), number + 1);
            after.setStart_scale(rangeHigh + 1);
            saveDescription(after);
        }
        return description;
    }

    @Transactional
    public void deleteDescriptionById(Description description, Questionnaire questionnaire) {
        if (description != null) {
            int number = description.getNumber();
            int diff = description.getEnd_scale() - description.getStart_scale() + 1;

            if (number - 2 >= 0 && number < questionnaire.getDescriptions().size()) {
                Description before = questionnaire.getDescriptions().get(number - 2);
                Description after = questionnaire.getDescriptions().get(number);
                after.setStart_scale(after.getStart_scale() - diff / 2);
                saveDescription(before);
                saveDescription(after);
                if ((diff % 2 == 1)) {
                    before.setEnd_scale(before.getEnd_scale() + diff / 2 + 1);
                } else {
                    before.setEnd_scale(before.getEnd_scale() + diff / 2);
                }
            } else if (number - 2 < 0 && number < questionnaire.getDescriptions().size()) {
                Description after = questionnaire.getDescriptions().get(number);
                after.setStart_scale(1);
                saveDescription(after);
            } else if (number >= questionnaire.getDescriptions().size() && number - 2 >= 0) {
                Description before = questionnaire.getDescriptions().get(number - 2);
                before.setEnd_scale(description.getEnd_scale());
                saveDescription(before);
            }

            for (int i = 0; i < questionnaire.getDescriptions().size() - description.getNumber(); i++) {
                Description description1 = questionnaire.getDescriptions().get(number);
                description1.setNumber(description1.getNumber() - 1);
                saveDescription(description1);
            }
            descriptionRepository.deleteDescriptionById(description.getId());
        }
    }

    public int minimum(List<Description> descriptions) {
        int minimum = 1;
        for (int i = 0; i < descriptions.size(); i++) {
            if (minimum < descriptions.get(i).getEnd_scale())
                minimum = descriptions.get(i).getEnd_scale() + 1;
        }
        return minimum;
    }
}
