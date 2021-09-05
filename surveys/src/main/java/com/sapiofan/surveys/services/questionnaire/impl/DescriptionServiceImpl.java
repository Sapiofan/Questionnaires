package com.sapiofan.surveys.services.questionnaire.impl;

import com.sapiofan.surveys.entities.questionnaire.Description;
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
    public Description findDescriptionByNumber(Long questionnaireId, Integer number){
        return descriptionRepository.findDescriptionByNumber(questionnaireId, number);
    }

    @Transactional
    public void saveDescription(Description description) {
        descriptionRepository.save(description);
    }

    @Transactional
    public void deleteDescriptionById(Long id) {
        descriptionRepository.deleteDescriptionById(id);
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
