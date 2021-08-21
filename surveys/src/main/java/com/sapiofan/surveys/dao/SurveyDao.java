package com.sapiofan.surveys.dao;

import com.sapiofan.surveys.entities.Survey;

import java.util.List;

public interface SurveyDao {
    List<Survey> findAllSurveys();
    Survey findByNickName();
    Survey findBySurveyName();
    void addQuestion();
    void deleteQuestion();
}
