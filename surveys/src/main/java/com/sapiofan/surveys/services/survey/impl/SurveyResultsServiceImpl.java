package com.sapiofan.surveys.services.survey.impl;

import com.sapiofan.surveys.entities.survey.Survey;
import com.sapiofan.surveys.entities.survey.SurveyResults;
import com.sapiofan.surveys.repository.survey.SurveyResultsRepository;
import com.sapiofan.surveys.security.realization.CustomUserDetails;
import com.sapiofan.surveys.services.survey.SurveyResultsService;
import com.sapiofan.surveys.services.survey.SurveyService;
import com.sapiofan.surveys.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Service
public class SurveyResultsServiceImpl implements SurveyResultsService {

    @Autowired
    private SurveyResultsRepository surveyResultsRepository;

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private UserService userService;

    @Transactional
    public SurveyResults findSurveyResultsById(UUID id) {
        return surveyResultsRepository.findSurveyResultsById(id);
    }

    @Transactional
    public SurveyResults createSurveyResults(Authentication authentication, Long id) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        Survey survey = surveyService.findSurveyById(id);
        SurveyResults results = new SurveyResults();
        results.setSurvey(survey);
        results.setUser(userService.findUserByNickname(principal.getUsername()));
        Timestamp ts = Timestamp.from(Instant.now());
        results.setStart(ts);
        results.setEnd_time(ts);
        surveyResultsRepository.save(results);
        return results;
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
