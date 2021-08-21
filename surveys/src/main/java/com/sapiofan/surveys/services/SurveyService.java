package com.sapiofan.surveys.services;

import com.sapiofan.surveys.entities.Survey;

import java.util.List;

public interface SurveyService {
    List<Survey> findAllSurveys();
    Survey findByNickName();
    Survey findBySurveyName();
    void addQuestion();
    void deleteQuestion();
}
