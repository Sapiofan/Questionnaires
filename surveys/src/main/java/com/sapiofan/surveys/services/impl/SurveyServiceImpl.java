package com.sapiofan.surveys.services.impl;

import com.sapiofan.surveys.repository.QuestionRepository;
import com.sapiofan.surveys.repository.SurveyRepository;
import com.sapiofan.surveys.entities.Question;
import com.sapiofan.surveys.entities.Survey;
import com.sapiofan.surveys.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private QuestionRepository questionDao;


    @Override
    public List<Survey> findAllSurveys() {
        return surveyRepository.findAllSurveys();
    }

    @Override
    public List<Question> findAllQuestions(Long survey_id) {
        return questionDao.findAllQuestions(survey_id);
    }

    @Override
    public Survey findSurveyByNickName(String nickname) {
        return surveyRepository.findSurveyByNickName(nickname);
    }

    @Override
    public Survey findBySurveyName(String name) {
        return surveyRepository.findBySurveyName(name);
    }

    @Override
    public void save(Survey survey) {
        surveyRepository.save(survey);
    }


    @Override
    public void deleteQuestion(Long id) {
        questionDao.deleteQuestion(id);
    }

    @Override
    public void updateQuestion(String description, Long id) {
        questionDao.updateQuestion(description, id);
    }


}
