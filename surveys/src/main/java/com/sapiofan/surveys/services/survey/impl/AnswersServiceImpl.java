package com.sapiofan.surveys.services.survey.impl;

import com.sapiofan.surveys.entities.survey.Answer;
import com.sapiofan.surveys.repository.survey.AnswerRepository;
import com.sapiofan.surveys.services.survey.AnswersService;
import com.sapiofan.surveys.services.survey.SurveyQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnswersServiceImpl implements AnswersService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private SurveyQuestionService surveyQuestionService;

    @Transactional
    public List<Answer> findAllAnswers(Long question_id) {
        return answerRepository.findAllAnswers(question_id);
    }

    @Transactional
    public Answer findAnswerById(Long id) {
        return answerRepository.findAnswerById(id);
    }

    @Transactional
    public Answer findAnswerByNumber(Long questionId, Integer number) {
        return answerRepository.findAnswerByNumber(questionId, number);
    }

    @Transactional
    public void saveAnswer(Answer answer) {
        answerRepository.save(answer);
    }

    @Transactional
    public Answer updateAnswer(Long answerId, String inputtedAnswer, String correctness, Integer number, Long questionId) {
        Answer answer = findAnswerById(answerId);
        answer.setAnswer(inputtedAnswer);
        answer.setCorrectness(correctness.equals("1"));
        if (number <= 0 || number > findAllAnswers(questionId).size()) {
        } else if (number.equals(answer.getNumber())) {
        } else {
            if (number > answer.getNumber()) {
                for (int i = 1; i < number - answer.getNumber() + 1; i++) {
                    Answer answer1 = surveyQuestionService.findQuestionById(questionId).getAnswers().get(answer.getNumber() + i - 1);
                    answer1.setNumber(answer1.getNumber() - 1);
                    saveAnswer(answer1);
                }
            } else {
                for (int i = 0; i < answer.getNumber() - number; i++) {
                    Answer answer1 = surveyQuestionService.findQuestionById(questionId).getAnswers().get(number + i - 1);
                    answer1.setNumber(answer1.getNumber() + 1);
                    saveAnswer(answer1);
                }
            }
            answer.setNumber(number);
        }
        saveAnswer(answer);
        return answer;
    }

    @Transactional
    public void deleteAnswerByNumber(Long questionId, Integer number) {
        Answer answer = answerRepository.findAnswerByNumber(questionId, number);
        for (int i = 1; i <= findAllAnswers(questionId).size() - answer.getNumber(); i++) {
            Answer answer1 = answerRepository.findAnswerByNumber(questionId, number + i);
            answer1.setNumber(answer1.getNumber() - 1);
            saveAnswer(answer1);
        }
        answerRepository.deleteAnswerById(answer.getId());
    }

}
