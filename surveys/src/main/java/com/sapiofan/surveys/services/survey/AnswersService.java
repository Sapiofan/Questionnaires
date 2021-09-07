package com.sapiofan.surveys.services.survey;

import com.sapiofan.surveys.entities.survey.Answer;
import com.sapiofan.surveys.entities.survey.Question;

import java.util.List;

public interface AnswersService {

    List<Answer> findAllAnswers(Long question_id);

    Answer findAnswerById(Long id);

    Answer findAnswerByNumber(Long questionId, Integer number);

    void saveAnswer(Answer answer);

    Answer createAnswer(Question question, String inputtedAnswer, String correctness);

    Answer updateAnswer(Long answerId, String inputtedAnswer, String correctness, Integer number, Long questionId);

    void deleteAnswerByNumber(Long questionId, Integer number);
}
