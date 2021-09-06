package com.sapiofan.surveys.services.questionnaire.impl;

import com.sapiofan.surveys.entities.questionnaire.Questionnaire;
import com.sapiofan.surveys.entities.questionnaire.QuestionnaireResult;
import com.sapiofan.surveys.repository.questionnaire.QuestionnaireResultRepository;
import com.sapiofan.surveys.security.realization.CustomUserDetails;
import com.sapiofan.surveys.services.questionnaire.QuestionnaireResultsService;
import com.sapiofan.surveys.services.questionnaire.QuestionnaireService;
import com.sapiofan.surveys.services.user.UserService;
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

    @Transactional
    public QuestionnaireResult findQuestionnaireResultById(UUID id) {
        return questionnaireResultRepository.findQuestionnaireResultById(id);
    }

    @Transactional
    public void saveQuestionnaireResult(QuestionnaireResult questionnaireResult) {
        questionnaireResultRepository.save(questionnaireResult);
    }

    @Transactional
    public QuestionnaireResult createQuestionnaireResult(Authentication authentication, Long id) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(id);

        QuestionnaireResult result = new QuestionnaireResult();
        result.setQuestionnaire(questionnaire);
        result.setUser(userService.findUserByNickname(principal.getUsername()));
        Timestamp ts = Timestamp.from(Instant.now());
        result.setStart(ts);
        result.setEnd_time(ts);
        saveQuestionnaireResult(result);
        return result;
    }

    @Transactional
    public void deleteResultsById(UUID resultId) {
        questionnaireResultRepository.deleteQuestionnaireResultById(resultId);
    }
}
