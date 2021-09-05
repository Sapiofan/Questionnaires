package com.sapiofan.surveys.services.survey.impl;

import com.sapiofan.surveys.entities.survey.Answer;
import com.sapiofan.surveys.entities.survey.Survey;
import com.sapiofan.surveys.repository.survey.SurveyRepository;
import com.sapiofan.surveys.services.survey.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class SurveyServiceImpl implements SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;
//    @Autowired
//    private QuestionRepository questionRepository;
//
//    @Autowired
//    private AnswerRepository answerRepository;
//
//    @Autowired
//    private SurveyResultsRepository surveyResultsRepository;
//
//    @Autowired
//    private RightAnswersRepository rightAnswersRepository;


    @Transactional
    public List<Survey> findAllSurveys() {
        return surveyRepository.findAllSurveys();
    }
//    @Transactional
//    public List<RightAnswers> results(UUID id){
//        return rightAnswersRepository.findAllResultsBySurvey(id);
//    }
//
//    @Transactional
//    public List<Question> findAllQuestions(Long survey_id) {
//        return questionRepository.findAllQuestions(survey_id);
//    }
//    @Transactional
//    public List<Answer> findAllAnswers(Long question_id){
//        return answerRepository.findAllAnswers(question_id);
//    }
//    @Transactional
//    public SurveyResults findSurveyResultsById(UUID id){
//        return surveyResultsRepository.findSurveyResultsById(id);
//    }
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

    @Transactional
    public void save(Survey survey) {
        surveyRepository.save(survey);
    }

    //    @Transactional
//    public void saveQuestion(Question question){
//        questionRepository.save(question);
//    }
//    @Transactional
//    public void saveAnswer(Answer answer){
//        answerRepository.save(answer);
//    }
//    @Transactional
//    public void saveSurveyResults(SurveyResults surveyResults){
//        surveyResultsRepository.save(surveyResults);
//    }
//    @Transactional
//    public void saveResult(RightAnswers answers){
//        rightAnswersRepository.save(answers);
//    }
//    @Transactional
//    public void deleteAnswerById(Long id){
//        answerRepository.deleteAnswerById(id);
//    }
//    @Transactional
//    public void deleteQuestionById(Long id){
//        questionRepository.deleteQuestion(id);
//    }
    @Transactional
    public void deleteSurveyById(Long id) {
        surveyRepository.deleteById(id);
    }

    //    @Transactional
//    public void deleteResultsById(UUID id){
//        surveyResultsRepository.deleteResultsById(id);
//    }
    @Transactional
    public Survey findSurveyById(Long id) {
        return surveyRepository.findSurveyById(id);
    }

    public boolean checkInput(List<Answer> answers) {
        int counter = 0;
        for (Answer answer : answers) {
            if (answer.getCorrectness()) {
                counter++;
            }
        }
        if (counter == 0 || counter >= 2) {
            return false;
        } else {
            return true;
        }
    }
//    @Transactional
//    public Question findQuestionById(Long id){
//        return questionRepository.findQuestionById(id);
//    }
//    @Transactional
//    public Answer findAnswerById(Long id){
//        return answerRepository.findAnswerById(id);
//    }
//    @Transactional
//    public Question findQuestionByNumber(Long survey_id, Integer number){
//        return questionRepository.findQuestionByNumber(survey_id, number);
//    }
}
