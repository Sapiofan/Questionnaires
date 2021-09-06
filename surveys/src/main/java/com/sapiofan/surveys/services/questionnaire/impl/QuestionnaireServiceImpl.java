package com.sapiofan.surveys.services.questionnaire.impl;

import com.sapiofan.surveys.entities.questionnaire.Questionnaire;
import com.sapiofan.surveys.entities.questionnaire.Scale;
import com.sapiofan.surveys.repository.questionnaire.QuestionnaireRepository;
import com.sapiofan.surveys.security.realization.CustomUserDetails;
import com.sapiofan.surveys.services.questionnaire.QuestionnaireService;
import com.sapiofan.surveys.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public Questionnaire findQuestionnaireById(Long id) {
        return questionnaireRepository.findQuestionnaireById(id);
    }

    @Transactional
    public List<Questionnaire> findAllQuestionnaires() {
        return questionnaireRepository.findAllQuestionnaires();
    }

    @Transactional
    public void saveQuestionnaire(Questionnaire questionnaire) {
        questionnaireRepository.save(questionnaire);
    }

    @Transactional
    public Questionnaire createQuestionnaire(Authentication authentication, Long questionnaireId,
                                             String name, String description, Integer scale) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        Questionnaire questionnaire;
        if (questionnaireId == 0) {
            questionnaire = new Questionnaire();
            questionnaire.setUser(userService.findUserByNickname(principal.getUsername()));
        } else {
            questionnaire = findQuestionnaireById(questionnaireId);
        }
        questionnaire.setName(name);
        questionnaire.setGeneral_description(description);
        questionnaire.setSize(0);
        if (scale == 5) {
            questionnaire.setScale(Scale.FIVE);
        } else {
            questionnaire.setScale(Scale.TEN);
        }
        saveQuestionnaire(questionnaire);
        return questionnaire;
    }

    @Transactional
    public void deleteQuestionnaire(Long id) {
        questionnaireRepository.deleteQuestionnaireById(id);
    }

    public int maximum(Questionnaire questionnaire) {
        int max;
        if (questionnaire.getScale().equals(Scale.FIVE))
            max = 5;
        else
            max = 10;
        max *= questionnaire.getQuestions().size();
        return max;
    }
}
