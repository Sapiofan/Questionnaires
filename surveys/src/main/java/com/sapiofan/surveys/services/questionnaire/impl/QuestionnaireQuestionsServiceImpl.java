package com.sapiofan.surveys.services.questionnaire.impl;

import com.sapiofan.surveys.entities.questionnaire.QQuestion;
import com.sapiofan.surveys.entities.questionnaire.Questionnaire;
import com.sapiofan.surveys.repository.questionnaire.QQuestionRepository;
import com.sapiofan.surveys.services.questionnaire.QuestionnaireQuestionsService;
import com.sapiofan.surveys.services.questionnaire.QuestionnaireService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionnaireQuestionsServiceImpl implements QuestionnaireQuestionsService {

    @Autowired
    private QQuestionRepository questionRepository;

    @Autowired
    private QuestionnaireService questionnaireService;

    private static final Logger log = LoggerFactory.getLogger("log");

    @Transactional
    public QQuestion findQuestionByNumber(Long questionnaire_id, Integer number) {
        log.info("finding of question by questionnaire id " + questionnaire_id + " and number " + number);
        return questionRepository.findQuestionByNumber(questionnaire_id, number);
    }

    @Transactional
    public void saveQQuestion(QQuestion question) {
        log.info("saving of question id = " + question.getId());
        questionRepository.save(question);
        log.info("question was successfully saved");
    }

    @Transactional
    public QQuestion createQQuestion(Long questionnaireId, String inputtedQuestion) {
        log.info("start of creating of question");
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
        List<QQuestion> qQuestions = findAllQuestions(questionnaireId);
        for (QQuestion qQuestion : qQuestions) {
            if (qQuestion.getName().equals(inputtedQuestion)) {
                return qQuestion;
            }
        }
        QQuestion question = new QQuestion();
        question.setNumber(questionnaire.getQuestions().size() + 1);
        question.setName(inputtedQuestion);
        question.setQuestionnaire(questionnaire);
        saveQQuestion(question);
        log.info("question was created. Id = " + question.getId() + ", name = " + question.getName());
        return question;
    }

    @Transactional
    public void changeQuestionNumber(Integer from, Integer to, Long questionnaireId){
        List<QQuestion> questions = findAllQuestions(questionnaireId);
        int size = questions.size();
        if(size != 0 && from <= size && from > 0 && to <= size && to > 0) {
            QQuestion question = findQuestionByNumber(questionnaireId, from);
            if (from > to) {
                for (int i = 1; i < from - to + 1; i++) {
                    QQuestion question1 = findQuestionByNumber(questionnaireId, from - i);
                    question1.setNumber(question1.getNumber() + 1);
                    questionRepository.save(question1);
                }
            } else if (to > from) {
                for (int i = 1; i < to - from + 1; i++) {
                    QQuestion question1 = findQuestionByNumber(questionnaireId, from + i);
                    question1.setNumber(question1.getNumber() - 1);
                    questionRepository.save(question1);
                }
            }
            question.setNumber(to);
            saveQQuestion(question);
        }
    }

    @Transactional
    public List<QQuestion> findAllQuestions(Long id) {
        log.info("finding of all questions by questionnaire id " + id);
        return questionRepository.findAllQuestions(id)
                .stream()
                .sorted(Comparator.comparingInt(QQuestion::getNumber))
                .collect(Collectors.toList());
    }

    @Transactional
    public QQuestion findQuestionById(Long id){
        log.info("find questionnaire question by Id = " + id);
        return questionRepository.findQuestionById(id);
    }

    @Transactional
    public void deleteQQuestionById(Long questionnaireId, Long questionId) {
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
        QQuestion question = findQuestionById(questionId);
        if(question != null) {
            log.warn("start of deleting of question id = " + question.getId());
            int number = question.getNumber();
            for (int i = 1; i <= questionnaire.getQuestions().size() - question.getNumber(); i++) {
                QQuestion question1 = findQuestionByNumber(questionnaireId, number + i);
                question1.setNumber(question1.getNumber() - 1);
                saveQQuestion(question1);
            }
            questionRepository.deleteQQuestionById(question.getId());
            log.warn("description was deleted");
        }
    }
}
