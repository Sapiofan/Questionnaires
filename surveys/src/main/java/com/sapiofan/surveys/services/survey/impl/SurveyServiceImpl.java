package com.sapiofan.surveys.services.survey.impl;

import com.sapiofan.surveys.entities.survey.Answer;
import com.sapiofan.surveys.entities.survey.Survey;
import com.sapiofan.surveys.repository.survey.SurveyRepository;
import com.sapiofan.surveys.security.realization.CustomUserDetails;
import com.sapiofan.surveys.services.survey.SurveyService;
import com.sapiofan.surveys.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class SurveyServiceImpl implements SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public List<Survey> findAllSurveys() {
        return surveyRepository.findAllSurveys();
    }

    @Transactional
    public Survey createSurvey(Long surveyId, String name, Authentication authentication) {
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        Survey survey;
        if (surveyId == 0) {
            survey = new Survey();
            survey.setName(name);
            survey.setSize(0);
            survey.setUser(userService.findUserByNickname(principal.getUsername()));
        } else {
            survey = findSurveyById(surveyId);
            survey.setName(name);
        }
        surveyRepository.save(survey);
        return survey;
    }

    @Transactional
    public void save(Survey survey) {
        surveyRepository.save(survey);
    }

    @Transactional
    public void deleteSurveyById(Long id) {
        surveyRepository.deleteById(id);
    }

    @Transactional
    public Survey findSurveyById(Long id) {
        return surveyRepository.findSurveyById(id);
    }

    public boolean checkInput(List<Answer> answers) {
        int counter = 0;
        for (Answer answer : answers) {
            if (answer.getCorrectness()) {
                counter++;
            }
        }
        if (counter == 0 || counter >= 2) {
            return false;
        } else {
            return true;
        }
    }
}
