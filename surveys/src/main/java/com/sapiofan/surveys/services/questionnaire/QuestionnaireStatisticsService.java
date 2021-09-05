package com.sapiofan.surveys.services.questionnaire;

import com.sapiofan.surveys.entities.questionnaire.EvaluatedQuestion;

import java.util.List;
import java.util.UUID;

public interface QuestionnaireStatisticsService {

    List<EvaluatedQuestion> findAllEvaluatedQuestions(UUID id);

    void saveEvaluatedQuestion(EvaluatedQuestion evaluatedQuestion);
}
