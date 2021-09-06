package com.sapiofan.surveys.services.survey.impl;

import com.sapiofan.surveys.entities.survey.Question;
import com.sapiofan.surveys.entities.survey.Survey;
import com.sapiofan.surveys.repository.survey.QuestionRepository;
import com.sapiofan.surveys.services.survey.SurveyQuestionService;
import com.sapiofan.surveys.services.survey.SurveyService;
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

    @Transactional
    public List<Question> findAllQuestions(Long survey_id) {
        return questionRepository.findAllQuestions(survey_id);
    }

    @Transactional
    public Question findQuestionById(Long id) {
        return questionRepository.findQuestionById(id);
    }

    @Transactional
    public Question findQuestionByNumber(Long survey_id, Integer number) {
        return questionRepository.findQuestionByNumber(survey_id, number);
    }

    @Transactional
    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }

    @Transactional
    public Question createQuestion(Long questionId, Long surveyId, String inputtedQuestion) {
        Question question;
        if (questionId == 0) {
            Survey survey = surveyService.findSurveyById(surveyId);
            question = new Question(survey.getQuestions().size() + 1, inputtedQuestion, survey);
        } else {
            question = findQuestionById(questionId);
            question.setDescription(inputtedQuestion);
        }
        saveQuestion(question);
        return question;
    }

    @Transactional
    public void deleteQuestionByNumber(Long surveyId, Integer number) {
        Question question = questionRepository.findQuestionByNumber(surveyId, number);
        for (int i = 1; i <= findAllQuestions(surveyId).size() - question.getNumber(); i++) {
            Question question1 = questionRepository.findQuestionByNumber(surveyId, number + i);
            question1.setNumber(question1.getNumber() - 1);
            saveQuestion(question1);
        }
        questionRepository.deleteQuestion(question.getId());
    }
}
