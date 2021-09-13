package com.sapiofan.surveys.services.questionnaire;

import com.sapiofan.surveys.entities.questionnaire.Description;
import com.sapiofan.surveys.entities.questionnaire.Questionnaire;

import java.util.List;

public interface DescriptionService {

    Description findDescriptionById(Long id);

    List<Description> findAllDescriptions(Long id);

    Description findDescriptionByNumber(Long questionnaireId, Integer number);

    void saveDescription(Description description);

    Description createDescription(String inputtedDescription, Questionnaire questionnaire,
                                  Integer minimum, Integer range);

    Description updateDescription(Long descriptionId, String inputtedDescription,
                                  Integer rangeLow, Integer rangeHigh, Questionnaire questionnaire);

    void deleteDescriptionById(Long descriptionId, Questionnaire questionnaire);

    int minimum(List<Description> descriptions);
}
