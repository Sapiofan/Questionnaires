package com.sapiofan.surveys.services.survey.impl;

import com.sapiofan.surveys.entities.survey.Survey;
import com.sapiofan.surveys.entities.survey.SurveyResults;
import com.sapiofan.surveys.repository.survey.SurveyResultsRepository;
import com.sapiofan.surveys.security.realization.CustomUserDetails;
import com.sapiofan.surveys.services.survey.SurveyResultsService;
import com.sapiofan.surveys.services.survey.SurveyService;
import com.sapiofan.surveys.services.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger("log");

    @Transactional
    public SurveyResults findSurveyResultsById(UUID id) {
        log.info("finding of survey result by id " + id);
        return surveyResultsRepository.findSurveyResultsById(id);
    }

    @Transactional
    public SurveyResults createSurveyResults(Authentication authentication, Long id) {
        log.info("start of creating of survey result");
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        Survey survey = surveyService.findSurveyById(id);
        SurveyResults results = new SurveyResults();
        results.setSurvey(survey);
        results.setUser(userService.findUserByNickname(principal.getUsername()));
        Timestamp ts = Timestamp.from(Instant.now());
        results.setStart(ts);
        results.setEnd_time(ts);
        surveyResultsRepository.save(results);
        log.info("survey result was created. Id = " + results.getId());
        return results;
    }

    @Transactional
    public void saveSurveyResults(SurveyResults surveyResults) {
        log.info("saving of survey result id = " + surveyResults.getId());
        surveyResultsRepository.save(surveyResults);
        log.info("survey result was successfully saved");
    }

    @Transactional
    public void deleteResultsById(UUID id) {
        log.warn("start of deleting of survey results id = " + id);
        surveyResultsRepository.deleteResultsById(id);
        log.warn("survey results was deleted");
    }
}
