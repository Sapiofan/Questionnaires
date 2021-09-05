package com.sapiofan.surveys.services.survey;

import com.sapiofan.surveys.entities.survey.Answer;

import java.util.List;

public interface AnswersService {

    List<Answer> findAllAnswers(Long question_id);

    Answer findAnswerById(Long id);

    void saveAnswer(Answer answer);

    void deleteAnswerById(Long id);
}
