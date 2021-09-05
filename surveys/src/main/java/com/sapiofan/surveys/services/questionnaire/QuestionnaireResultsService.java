package com.sapiofan.surveys.services.questionnaire;

import com.sapiofan.surveys.entities.questionnaire.QuestionnaireResult;

import java.util.UUID;

public interface QuestionnaireResultsService {

    QuestionnaireResult findQuestionnaireResultById(UUID id);

    void saveQuestionnaireResult(QuestionnaireResult questionnaireResult);

    void deleteResultsById(UUID resultId);
}
