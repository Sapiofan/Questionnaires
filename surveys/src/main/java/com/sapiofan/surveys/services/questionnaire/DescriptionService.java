package com.sapiofan.surveys.services.questionnaire;

import com.sapiofan.surveys.entities.questionnaire.Description;

import java.util.List;

public interface DescriptionService {

    Description findDescriptionById(Long id);

    List<Description> findAllDescriptions(Long id);

    void saveDescription(Description description);

    void deleteDescriptionById(Long id);

    int minimum(List<Description> descriptions);
}
