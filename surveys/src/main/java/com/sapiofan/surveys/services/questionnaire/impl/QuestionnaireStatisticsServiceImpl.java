package com.sapiofan.surveys.services.questionnaire.impl;

import com.sapiofan.surveys.entities.questionnaire.EvaluatedQuestion;
import com.sapiofan.surveys.repository.questionnaire.EvaluatedQuestionRepository;
import com.sapiofan.surveys.services.questionnaire.QuestionnaireStatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class QuestionnaireStatisticsServiceImpl implements QuestionnaireStatisticsService {

    private static final Logger log = LoggerFactory.getLogger("log");

    @Autowired
    private EvaluatedQuestionRepository evaluatedQuestionRepository;

    @Transactional
    public List<EvaluatedQuestion> findAllEvaluatedQuestions(UUID id) {
        log.info("finding of all evaluated questions");
        return evaluatedQuestionRepository.findAllResultsBySurvey(id);
    }

    @Transactional
    public void saveEvaluatedQuestion(EvaluatedQuestion evaluatedQuestion) {
        log.info("saving of evaluated question id = " + evaluatedQuestion.getId());
        evaluatedQuestionRepository.save(evaluatedQuestion);
        log.info("evaluated question was saved");
    }
}
