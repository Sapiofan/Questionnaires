package com.sapiofan.surveys.services.questionnaire.impl;

import com.sapiofan.surveys.entities.questionnaire.EvaluatedQuestion;
import com.sapiofan.surveys.repository.questionnaire.EvaluatedQuestionRepository;
import com.sapiofan.surveys.services.questionnaire.QuestionnaireStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class QuestionnaireStatisticsServiceImpl implements QuestionnaireStatisticsService {

    @Autowired
    private EvaluatedQuestionRepository evaluatedQuestionRepository;

    @Transactional
    public List<EvaluatedQuestion> findAllEvaluatedQuestions(UUID id) {
        return evaluatedQuestionRepository.findAllResultsBySurvey(id);
    }

    @Transactional
    public void saveEvaluatedQuestion(EvaluatedQuestion evaluatedQuestion) {
        evaluatedQuestionRepository.save(evaluatedQuestion);
    }
}
