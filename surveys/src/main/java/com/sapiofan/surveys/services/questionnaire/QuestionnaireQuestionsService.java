package com.sapiofan.surveys.services.questionnaire;

import com.sapiofan.surveys.entities.questionnaire.QQuestion;

import java.util.List;

public interface QuestionnaireQuestionsService {

    QQuestion findQuestionByNumber(Long questionnaire_id, Integer number);

    List<QQuestion> findAllQuestions(Long id);

    void saveQQuestion(QQuestion question);

    QQuestion createQQuestion(Long questionnaireId, String inputtedQuestion);

    void changeQuestionNumber(Integer from, Integer to, Long questionnaireId);

    void deleteQQuestionById(Long questionnaireId, Long questionId);
}
