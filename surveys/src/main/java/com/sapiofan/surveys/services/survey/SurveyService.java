package com.sapiofan.surveys.services.survey;

import com.sapiofan.surveys.entities.survey.Survey;

import java.util.List;

public interface SurveyService {
    List<Survey> findAllSurveys();
    Survey findSurveyById(Long id);

//    Survey findSurveyByNickName(String nickname);
//    Survey findBySurveyName(String name);

    void save(Survey survey);
    void deleteSurveyById(Long id);
}
