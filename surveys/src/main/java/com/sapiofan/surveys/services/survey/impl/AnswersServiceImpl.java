package com.sapiofan.surveys.services.survey.impl;

import com.sapiofan.surveys.entities.survey.Answer;
import com.sapiofan.surveys.repository.survey.AnswerRepository;
import com.sapiofan.surveys.services.survey.AnswersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnswersServiceImpl implements AnswersService {

    @Autowired
    private AnswerRepository answerRepository;

    @Transactional
    public List<Answer> findAllAnswers(Long question_id) {
        return answerRepository.findAllAnswers(question_id);
    }

    @Transactional
    public Answer findAnswerById(Long id) {
        return answerRepository.findAnswerById(id);
    }

    @Transactional
    public void saveAnswer(Answer answer) {
        answerRepository.save(answer);
    }

    @Transactional
    public void deleteAnswerById(Long id) {
        answerRepository.deleteAnswerById(id);
    }

}
