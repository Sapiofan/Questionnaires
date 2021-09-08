package com.sapiofan.surveys.services.questionnaire.impl;

import com.sapiofan.surveys.entities.questionnaire.Questionnaire;
import com.sapiofan.surveys.entities.questionnaire.QuestionnaireResult;
import com.sapiofan.surveys.repository.questionnaire.QuestionnaireResultRepository;
import com.sapiofan.surveys.security.realization.CustomUserDetails;
import com.sapiofan.surveys.services.questionnaire.QuestionnaireResultsService;
import com.sapiofan.surveys.services.questionnaire.QuestionnaireService;
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
public class QuestionnaireResultsServiceImpl implements QuestionnaireResultsService {

    @Autowired
    private QuestionnaireResultRepository questionnaireResultRepository;

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private UserService userService;

    private static final Logger log = LoggerFactory.getLogger("log");

    @Transactional
    public QuestionnaireResult findQuestionnaireResultById(UUID id) {
        log.info("finding of questionnaire result by id " + id);
        return questionnaireResultRepository.findQuestionnaireResultById(id);
    }

    @Transactional
    public void saveQuestionnaireResult(QuestionnaireResult questionnaireResult) {
        log.info("saving of questionnaire result id = " + questionnaireResult.getId());
        questionnaireResultRepository.save(questionnaireResult);
        log.info("questionnaire result was successfully saved");
    }

    @Transactional
    public QuestionnaireResult createQuestionnaireResult(Authentication authentication, Long id) {
        log.info("start of creating of questionnaire result");
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(id);

        QuestionnaireResult result = new QuestionnaireResult();
        result.setQuestionnaire(questionnaire);
        result.setUser(userService.findUserByNickname(principal.getUsername()));
        Timestamp ts = Timestamp.from(Instant.now());
        result.setStart(ts);
        result.setEnd_time(ts);
        saveQuestionnaireResult(result);
        log.info("questionnaire result was created. Id = " + result.getId());
        return result;
    }

    @Transactional
    public void deleteResultsById(UUID resultId) {
        log.warn("start of deleting of questionnaire result id = " + resultId);
        questionnaireResultRepository.deleteQuestionnaireResultById(resultId);
        log.warn("questionnaire result was deleted");
    }
}
