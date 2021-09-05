package com.sapiofan.surveys.services.survey.impl;

import com.sapiofan.surveys.entities.survey.Question;
import com.sapiofan.surveys.repository.survey.QuestionRepository;
import com.sapiofan.surveys.services.survey.SurveyQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SurveyQuestionServiceImpl implements SurveyQuestionService {

    @Autowired
    private QuestionRepository questionRepository;

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
    public void deleteQuestionById(Long id) {
        questionRepository.deleteQuestion(id);
    }
}
