package com.sapiofan.surveys.services.questionnaire.impl;

import com.sapiofan.surveys.entities.questionnaire.QuestionnaireResult;
import com.sapiofan.surveys.repository.questionnaire.QuestionnaireResultRepository;
import com.sapiofan.surveys.services.questionnaire.QuestionnaireResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class QuestionnaireResultsServiceImpl implements QuestionnaireResultsService {

    @Autowired
    private QuestionnaireResultRepository questionnaireResultRepository;

    @Transactional
    public QuestionnaireResult findQuestionnaireResultById(UUID id) {
        return questionnaireResultRepository.findQuestionnaireResultById(id);
    }

    @Transactional
    public void saveQuestionnaireResult(QuestionnaireResult questionnaireResult) {
        questionnaireResultRepository.save(questionnaireResult);
    }

    @Transactional
    public void deleteResultsById(UUID resultId) {
        questionnaireResultRepository.deleteQuestionnaireResultById(resultId);
    }
}
