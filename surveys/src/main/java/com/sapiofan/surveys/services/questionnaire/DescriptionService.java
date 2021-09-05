package com.sapiofan.surveys.services.questionnaire;

import com.sapiofan.surveys.entities.questionnaire.Description;

import java.util.List;

public interface DescriptionService {

    Description findDescriptionById(Long id);

    List<Description> findAllDescriptions(Long id);

    Description findDescriptionByNumber(Long questionnaireId, Integer number);

    void saveDescription(Description description);

    void deleteDescriptionById(Long id);

    int minimum(List<Description> descriptions);
}
