package com.sapiofan.surveys.services;

import com.sapiofan.surveys.entities.Question;
import com.sapiofan.surveys.entities.Survey;

import java.util.List;

public interface SurveyService {
    List<Survey> findAllSurveys();
    List<Question> findAllQuestions(Long survey_id);
    Survey findSurveyByNickName(String nickname);
    Survey findBySurveyName(String name);

    void save(Survey survey);

    void deleteQuestion(Long id);
    void updateQuestion(String description, Long id);


}
