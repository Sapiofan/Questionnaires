package com.sapiofan.surveys.services.survey;

import com.sapiofan.surveys.entities.survey.Question;

import java.util.List;

public interface SurveyQuestionService {
    List<Question> findAllQuestions(Long survey_id);

    Question findQuestionById(Long id);

    Question findQuestionByNumber(Long survey_id, Integer number);

    void saveQuestion(Question question);

    Question createQuestion(Long questionId, Long surveyId, String inputtedQuestion);

    void changeQuestionNumber(Integer from, Integer to, Long surveyId);

    void deleteQuestionByNumber(Long surveyId, Long questionId);

}
