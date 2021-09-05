package com.sapiofan.surveys.services.survey.impl;

import com.sapiofan.surveys.entities.survey.SurveyResults;
import com.sapiofan.surveys.repository.survey.SurveyResultsRepository;
import com.sapiofan.surveys.services.survey.SurveyResultsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class SurveyResultsServiceImpl implements SurveyResultsService {

    @Autowired
    private SurveyResultsRepository surveyResultsRepository;

    @Transactional
    public SurveyResults findSurveyResultsById(UUID id) {
        return surveyResultsRepository.findSurveyResultsById(id);
    }

    @Transactional
    public void saveSurveyResults(SurveyResults surveyResults) {
        surveyResultsRepository.save(surveyResults);
    }

    @Transactional
    public void deleteResultsById(UUID id) {
        surveyResultsRepository.deleteResultsById(id);
    }
}
