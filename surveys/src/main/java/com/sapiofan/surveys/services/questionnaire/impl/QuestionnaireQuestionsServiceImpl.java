package com.sapiofan.surveys.services.questionnaire.impl;

import com.sapiofan.surveys.entities.questionnaire.QQuestion;
import com.sapiofan.surveys.entities.questionnaire.Questionnaire;
import com.sapiofan.surveys.repository.questionnaire.QQuestionRepository;
import com.sapiofan.surveys.services.questionnaire.QuestionnaireQuestionsService;
import com.sapiofan.surveys.services.questionnaire.QuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuestionnaireQuestionsServiceImpl implements QuestionnaireQuestionsService {

    @Autowired
    private QQuestionRepository questionRepository;

    @Autowired
    private QuestionnaireService questionnaireService;

    @Transactional
    public QQuestion findQuestionByNumber(Long questionnaire_id, Integer number) {
        return questionRepository.findQuestionByNumber(questionnaire_id, number);
    }

    @Transactional
    public void saveQQuestion(QQuestion question) {
        questionRepository.save(question);
    }

    @Transactional
    public QQuestion createQQuestion(Long questionnaireId, String inputtedQuestion) {
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
        QQuestion question = new QQuestion();
        question.setNumber(questionnaire.getQuestions().size() + 1);
        question.setName(inputtedQuestion);
        question.setQuestionnaire(questionnaire);
        saveQQuestion(question);
        return question;
    }

    @Transactional
    public List<QQuestion> findAllQuestions(Long id) {
        return questionRepository.findAllQuestions(id);
    }

    @Transactional
    public void deleteQQuestionById(Long questionnaireId, Integer number) {
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
        QQuestion question = findQuestionByNumber(questionnaireId, number);
        for (int i = 1; i <= questionnaire.getQuestions().size() - question.getNumber(); i++) {
            QQuestion question1 = findQuestionByNumber(questionnaireId, number + i);
            question1.setNumber(question1.getNumber() - 1);
            saveQQuestion(question1);
        }
        questionRepository.deleteQQuestionById(question.getId());
    }
}
