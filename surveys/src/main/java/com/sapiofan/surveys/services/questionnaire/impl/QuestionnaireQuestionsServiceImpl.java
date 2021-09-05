package com.sapiofan.surveys.services.questionnaire.impl;

import com.sapiofan.surveys.entities.questionnaire.QQuestion;
import com.sapiofan.surveys.repository.questionnaire.QQuestionRepository;
import com.sapiofan.surveys.services.questionnaire.QuestionnaireQuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuestionnaireQuestionsServiceImpl implements QuestionnaireQuestionsService {

    @Autowired
    private QQuestionRepository questionRepository;

    @Transactional
    public QQuestion findQuestionByNumber(Long questionnaire_id, Integer number) {
        return questionRepository.findQuestionByNumber(questionnaire_id, number);
    }

    @Transactional
    public void saveQQuestion(QQuestion question) {
        questionRepository.save(question);
    }

    @Transactional
    public List<QQuestion> findAllQuestions(Long id) {
        return questionRepository.findAllQuestions(id);
    }

    @Transactional
    public void deleteQQuestionById(Long id) {
        questionRepository.deleteQQuestionById(id);
    }
}
