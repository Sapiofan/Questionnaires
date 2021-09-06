package com.sapiofan.surveys.services.questionnaire;

import com.sapiofan.surveys.entities.questionnaire.QuestionnaireResult;
import org.springframework.security.core.Authentication;

import java.util.UUID;

public interface QuestionnaireResultsService {

    QuestionnaireResult findQuestionnaireResultById(UUID id);

    void saveQuestionnaireResult(QuestionnaireResult questionnaireResult);

    QuestionnaireResult createQuestionnaireResult(Authentication authentication, Long id);

    void deleteResultsById(UUID resultId);
}
