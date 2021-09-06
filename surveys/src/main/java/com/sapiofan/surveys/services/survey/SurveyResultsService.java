package com.sapiofan.surveys.services.survey;

import com.sapiofan.surveys.entities.survey.SurveyResults;
import org.springframework.security.core.Authentication;

import java.util.UUID;

public interface SurveyResultsService {

    SurveyResults findSurveyResultsById(UUID id);

    SurveyResults createSurveyResults(Authentication authentication, Long id);

    void saveSurveyResults(SurveyResults surveyResults);

    void deleteResultsById(UUID id);
}
