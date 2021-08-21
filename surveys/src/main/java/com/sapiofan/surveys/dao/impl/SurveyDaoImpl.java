package com.sapiofan.surveys.dao.impl;

import com.sapiofan.surveys.dao.SurveyDao;
import com.sapiofan.surveys.entities.Survey;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SurveyDaoImpl implements SurveyDao {

    @Override
    public List<Survey> findAllSurveys() {
        return null;
    }

    @Override
    public Survey findByNickName() {
        return null;
    }

    @Override
    public Survey findBySurveyName() {
        return null;
    }

    @Override
    public void addQuestion() {

    }

    @Override
    public void deleteQuestion() {

    }
}
