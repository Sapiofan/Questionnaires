package com.sapiofan.surveys.services.survey.impl;

import com.sapiofan.surveys.entities.survey.Answer;
import com.sapiofan.surveys.entities.survey.Question;
import com.sapiofan.surveys.repository.survey.AnswerRepository;
import com.sapiofan.surveys.services.survey.AnswersService;
import com.sapiofan.surveys.services.survey.SurveyQuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswersServiceImpl implements AnswersService {

    private static final Logger log = LoggerFactory.getLogger("log");

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private SurveyQuestionService surveyQuestionService;

    @Transactional
    public List<Answer> findAllAnswers(Long question_id) {
        log.info("finding of all answers by question id " + question_id);
        return answerRepository.findAllAnswers(question_id)
                .stream()
                .sorted(Comparator.comparingInt(Answer::getNumber))
                .collect(Collectors.toList());
    }

    @Transactional
    public Answer findAnswerById(Long id) {
        log.info("finding of answer by id " + id);
        return answerRepository.findAnswerById(id);
    }

    @Transactional
    public Answer findAnswerByNumber(Long questionId, Integer number) {
        log.info("finding of answer by question id " + questionId + " and number " + number);
        return answerRepository.findAnswerByNumber(questionId, number);
    }

    @Transactional
    public void saveAnswer(Answer answer) {
        log.info("saving of answer id = " + answer.getId());
        answerRepository.save(answer);
        log.info("answer was successfully saved");
    }

    @Transactional
    public Answer createAnswer(Question question, String inputtedAnswer, String correctness){
        log.info("start of creating of answer");
        List<Answer> answers = findAllAnswers(question.getId());
        for (Answer value : answers) {
            if (value.getAnswer().equals(inputtedAnswer)) {
                return value;
            }
        }
        Answer answer = new Answer(question.getAnswers().size() + 1, question, inputtedAnswer, correctness.equals("1"));
        saveAnswer(answer);
        log.info("answer was created. Id = " + answer.getId() + ", answer = " + answer.getAnswer());
        return answer;
    }

    @Transactional
    public Answer updateAnswer(Long answerId, String inputtedAnswer, String correctness, Integer number, Long questionId) {
        Answer answer = findAnswerById(answerId);
        log.info("updating of answer Id = " + answer.getId() + ", answer = " + answer.getAnswer());
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
        log.info("answer was updated");
        return answer;
    }

    @Transactional
    public void deleteAnswerByNumber(Long questionId, Integer number) {
        Answer answer = answerRepository.findAnswerByNumber(questionId, number);
        log.warn("start of deleting of answer id = " + answer.getId());
        for (int i = 1; i <= findAllAnswers(questionId).size() - answer.getNumber(); i++) {
            Answer answer1 = answerRepository.findAnswerByNumber(questionId, number + i);
            answer1.setNumber(answer1.getNumber() - 1);
            saveAnswer(answer1);
        }
        answerRepository.deleteAnswerById(answer.getId());
        log.warn("description was deleted");
    }
}
