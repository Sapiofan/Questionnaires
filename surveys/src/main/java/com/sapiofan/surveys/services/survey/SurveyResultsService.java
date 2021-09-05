package com.sapiofan.surveys.services.survey;

import com.sapiofan.surveys.entities.survey.SurveyResults;

import java.util.UUID;

public interface SurveyResultsService {

    SurveyResults findSurveyResultsById(UUID id);

    void saveSurveyResults(SurveyResults surveyResults);

    void deleteResultsById(UUID id);
}
