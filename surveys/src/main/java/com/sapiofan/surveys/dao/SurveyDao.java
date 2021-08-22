package com.sapiofan.surveys.dao;

import com.sapiofan.surveys.entities.Question;
import com.sapiofan.surveys.entities.Survey;

import java.util.List;

public interface SurveyDao {
    List<Survey> findAllSurveys();
    List<Question> findAllQuestions(Long survey_id);
    Survey findSurveyByNickName(String nickname);
    Survey findBySurveyName(String name);
    void addQuestion(Question question);
    void deleteQuestion();

    Long createSurvey(String name);
}
