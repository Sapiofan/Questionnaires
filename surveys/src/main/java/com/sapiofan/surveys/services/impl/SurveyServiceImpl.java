package com.sapiofan.surveys.services.impl;

import com.sapiofan.surveys.entities.*;
import com.sapiofan.surveys.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
public class SurveyServiceImpl {

    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private SurveyResultsRepository surveyResultsRepository;

    @Autowired
    private RightAnswersRepository rightAnswersRepository;



    private QuestionRepository questionDao;


//    @Override
    public List<Survey> findAllSurveys() {
        return surveyRepository.findAllSurveys();
    }

    public List<RightAnswers> results(UUID id){
        return rightAnswersRepository.findAllResultsBySurvey(id);
    }

//    @Override
    public List<Question> findAllQuestions(Long survey_id) {
        return questionRepository.findAllQuestions(survey_id);
    }

    public List<Answer> findAllAnswers(Long question_id){
        return answerRepository.findAllAnswers(question_id);
    }

    public User findUserById(Long id){
        return surveyRepository.findUserById(id);
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
    @Transactional
    public void deleteQuestionById(Long id){
        questionRepository.deleteQuestion(id);
    }
    @Transactional
    public void deleteSurveyById(Long id){
        surveyRepository.deleteById(id);
    }
    @Transactional
    public void deleteResultsById(UUID id){
        surveyResultsRepository.deleteResultsById(id);
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
//    @Override
//    public List<Question> findAllQuestions(Long survey_id) {
//        return questionDao.findAllQuestions(survey_id);
//    }
//
//    @Override
//    public Survey findSurveyByNickName(String nickname) {
//        return surveyRepository.findSurveyByNickName(nickname);
//    }
//
//    @Override
//    public Survey findBySurveyName(String name) {
//        return surveyRepository.findBySurveyName(name);
//    }
//
//    @Override
//    public void save(Survey survey) {
//        surveyRepository.save(survey);
//    }
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

}
