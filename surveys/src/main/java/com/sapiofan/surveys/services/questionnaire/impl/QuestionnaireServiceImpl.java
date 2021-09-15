package com.sapiofan.surveys.services.questionnaire.impl;

import com.sapiofan.surveys.entities.questionnaire.Questionnaire;
import com.sapiofan.surveys.entities.questionnaire.Scale;
import com.sapiofan.surveys.repository.questionnaire.QuestionnaireRepository;
import com.sapiofan.surveys.security.realization.CustomUserDetails;
import com.sapiofan.surveys.services.questionnaire.QuestionnaireService;
import com.sapiofan.surveys.services.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService {
    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @Autowired
    private UserService userService;

    private static final Logger log = LoggerFactory.getLogger("log");

    @Transactional
    public Questionnaire findQuestionnaireById(Long id) {
        log.info("finding of questionnaire by id " + id);
        return questionnaireRepository.findQuestionnaireById(id);
    }

    @Transactional
    public List<Questionnaire> findAllQuestionnaires() {
        log.info("finding of all questionnaires");
        return questionnaireRepository.findAllQuestionnaires()
                .stream()
                .sorted(Comparator.comparingInt(Questionnaire::getNumber))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<Questionnaire> findQuestionnaireByNickName(String nickname) {
        log.info("find all questionnaires of a certain user");
        return questionnaireRepository.findQuestionnaireByNickName(nickname)
                .stream()
                .sorted(Comparator.comparingInt(Questionnaire::getNumber))
                .collect(Collectors.toList());
    }

    @Transactional
    public List<Questionnaire> findByQuestionnaireName(String name) {
        log.info("find all questionnaires by name");
        return questionnaireRepository.findByQuestionnaireName(name)
                .stream()
                .sorted(Comparator.comparingInt(Questionnaire::getNumber))
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveQuestionnaire(Questionnaire questionnaire) {
        log.info("saving of questionnaire");
        questionnaireRepository.save(questionnaire);
        log.info("questionnaire successfully saved");
    }

    @Transactional
    public Questionnaire createQuestionnaire(Authentication authentication, Long questionnaireId,
                                             String name, String description, Integer scale) {
        log.info("start of creating of questionnaire");
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
        questionnaire.setNumber(findAllQuestionnaires().size() + 1);
        if (scale == 5) {
            questionnaire.setScale(Scale.FIVE);
        } else {
            questionnaire.setScale(Scale.TEN);
        }
        saveQuestionnaire(questionnaire);
        log.info("questionnaire was created. Id = " + questionnaire.getId() + ", name = " + questionnaire.getName());
        return questionnaire;
    }

    @Transactional
    public void deleteQuestionnaire(Long id) {
        Questionnaire questionnaire = findQuestionnaireById(id);
        log.warn("deleting of questionnaire " + id);
        int number = questionnaire.getNumber();
        for (int i = 1; i <= findAllQuestionnaires().size() - questionnaire.getNumber(); i++) {
            Questionnaire questionnaire1 = questionnaireRepository.findQuestionnaireByNumber(number + i);
            questionnaire1.setNumber(questionnaire1.getNumber() - 1);
            saveQuestionnaire(questionnaire1);
        }
        questionnaireRepository.deleteQuestionnaireById(id);
        log.warn("questionnaire has been deleted");
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
