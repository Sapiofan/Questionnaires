package com.sapiofan.surveys.services.survey.impl;

import com.sapiofan.surveys.entities.survey.Question;
import com.sapiofan.surveys.entities.survey.Survey;
import com.sapiofan.surveys.repository.survey.QuestionRepository;
import com.sapiofan.surveys.services.survey.SurveyQuestionService;
import com.sapiofan.surveys.services.survey.SurveyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SurveyQuestionServiceImpl implements SurveyQuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private SurveyService surveyService;

    private static final Logger log = LoggerFactory.getLogger("log");

    @Transactional
    public List<Question> findAllQuestions(Long survey_id) {
        log.info("finding of all questions by survey id " + survey_id);
        return questionRepository.findAllQuestions(survey_id);
    }

    @Transactional
    public Question findQuestionById(Long id) {
        log.info("finding of question by question id " + id);
        return questionRepository.findQuestionById(id);
    }

    @Transactional
    public Question findQuestionByNumber(Long survey_id, Integer number) {
        log.info("finding of question by survey id " + survey_id + " and number " + number);
        return questionRepository.findQuestionByNumber(survey_id, number);
    }

    @Transactional
    public void saveQuestion(Question question) {
        log.info("saving of question id = " + question.getId());
        questionRepository.save(question);
        log.info("question was successfully saved");
    }

    @Transactional
    public Question createQuestion(Long questionId, Long surveyId, String inputtedQuestion) {
        log.info("start of creating of question");
        Question question;
        if (questionId == 0) {
            Survey survey = surveyService.findSurveyById(surveyId);
            question = new Question(survey.getQuestions().size() + 1, inputtedQuestion, survey);
        } else {
            question = findQuestionById(questionId);
            question.setDescription(inputtedQuestion);
        }
        saveQuestion(question);
        log.info("question was created. Id = " + question.getId() + ", name = " + question.getDescription());
        return question;
    }

    @Transactional
    public void deleteQuestionByNumber(Long surveyId, Integer number) {
        Question question = questionRepository.findQuestionByNumber(surveyId, number);
        log.warn("start of deleting of question id = " + question.getId());
        for (int i = 1; i <= findAllQuestions(surveyId).size() - question.getNumber(); i++) {
            Question question1 = questionRepository.findQuestionByNumber(surveyId, number + i);
            question1.setNumber(question1.getNumber() - 1);
            saveQuestion(question1);
        }
        questionRepository.deleteQuestion(question.getId());
        log.warn("description was deleted");
    }
}
