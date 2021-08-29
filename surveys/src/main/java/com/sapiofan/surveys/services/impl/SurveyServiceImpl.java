package com.sapiofan.surveys.services.impl;

<<<<<<< HEAD
import com.sapiofan.surveys.entities.*;
import com.sapiofan.surveys.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class SurveyServiceImpl {
=======
import com.sapiofan.surveys.repository.QuestionRepository;
import com.sapiofan.surveys.repository.SurveyRepository;
import com.sapiofan.surveys.entities.Question;
import com.sapiofan.surveys.entities.Survey;
import com.sapiofan.surveys.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService {
>>>>>>> origin/main

    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
<<<<<<< HEAD
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private SurveyResultsRepository surveyResultsRepository;

    @Autowired
    private RightAnswersRepository rightAnswersRepository;



=======
    private QuestionRepository questionDao;


    @Override
>>>>>>> origin/main
    public List<Survey> findAllSurveys() {
        return surveyRepository.findAllSurveys();
    }

<<<<<<< HEAD
//    public List<RightAnswers> results(UUID id){
//        return rightAnswersRepository.findAllResultsBySurvey(id);
//    }

//    @Override
    public List<Question> findAllQuestions(Long survey_id) {
        return questionRepository.findAllQuestions(survey_id);
    }

    public List<Answer> findAllAnswers(Long question_id){
        return answerRepository.findAllAnswers(question_id);
    }

    public User findUserById(){
        return surveyRepository.findUserById();
    }

    public SurveyResults findSurveyResultsById(UUID id){
        return surveyResultsRepository.findSurveyResultsById(id);
    }
//
////    @Override
//    public Survey findSurveyByNickName(String nickname) {
//        return surveyRepository.findSurveyByNickName(nickname);
//    }
//
////    @Override
//    public Survey findBySurveyName(String name) {
//        return surveyRepository.findBySurveyName(name);
//    }

//    @Override
    @Transactional
    public void save(Survey survey) {
        surveyRepository.save(survey);
    }
    @Transactional
    public void saveQuestion(Question question){
        questionRepository.save(question);
    }
    @Transactional
    public void saveAnswer(Answer answer){
        answerRepository.save(answer);
    }
    @Transactional
    public void saveSurveyResults(SurveyResults surveyResults){
        surveyResultsRepository.save(surveyResults);
    }
    @Transactional
    public void saveResult(RightAnswers answers){
        rightAnswersRepository.save(answers);
    }
    @Transactional
    public void deleteAnswerById(Long id){
        answerRepository.deleteAnswerById(id);
    }

    public Survey findSurveyById(Long id){
        return surveyRepository.findSurveyById(id);
    }

    public Question findQuestionById(Long id){
        return questionRepository.findQuestionById(id);
    }

    public Answer findAnswerById(Long id){
        return answerRepository.findAnswerById(id);
    }
//
//
//    @Override
//    public void deleteQuestion(Long id) {
//        questionDao.deleteQuestion(id);
//    }
//
//    @Override
//    public void updateQuestion(String description, Long id) {
//        questionDao.updateQuestion(description, id);
//    }
=======
    @Override
    public List<Question> findAllQuestions(Long survey_id) {
        return questionDao.findAllQuestions(survey_id);
    }

    @Override
    public Survey findSurveyByNickName(String nickname) {
        return surveyRepository.findSurveyByNickName(nickname);
    }

    @Override
    public Survey findBySurveyName(String name) {
        return surveyRepository.findBySurveyName(name);
    }

    @Override
    public void save(Survey survey) {
        surveyRepository.save(survey);
    }


    @Override
    public void deleteQuestion(Long id) {
        questionDao.deleteQuestion(id);
    }

    @Override
    public void updateQuestion(String description, Long id) {
        questionDao.updateQuestion(description, id);
    }
>>>>>>> origin/main


}
