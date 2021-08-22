package com.sapiofan.surveys.services.impl;

import com.sapiofan.surveys.dao.SurveyDao;
import com.sapiofan.surveys.entities.Question;
import com.sapiofan.surveys.entities.Survey;
import com.sapiofan.surveys.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService {
    @Autowired
    private SurveyDao surveyDao;

    public SurveyServiceImpl(SurveyDao surveyDao) {
        this.surveyDao = surveyDao;
    }

    @Override
    public List<Survey> findAllSurveys() {
        return surveyDao.findAllSurveys();
    }

    @Override
    public List<Question> findAllQuestions(Long survey_id) {
        return surveyDao.findAllQuestions(survey_id);
    }

    @Override
    public Survey findSurveyByNickName(String nickname) {
        return surveyDao.findSurveyByNickName(nickname);
    }

    @Override
    public Survey findBySurveyName(String name) {
        return surveyDao.findBySurveyName(name);
    }

    @Override
    public void addQuestion() {
//        surveyDao.addQuestion();
    }

    @Override
    public void deleteQuestion() {
        surveyDao.deleteQuestion();
    }

    @Override
    public Long createSurvey(String name) {
        return surveyDao.createSurvey(name);
    }


}
