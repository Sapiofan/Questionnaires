package com.sapiofan.surveys.services.impl;

import com.sapiofan.surveys.entities.*;
import com.sapiofan.surveys.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class QuestionnaireServiceImpl {
    @Autowired
    QuestionnaireRepository questionnaireRepository;

    @Autowired
    QQuestionRepository questionRepository;

    @Autowired
    DescriptionRepository descriptionRepository;

    @Autowired
    QuestionnaireResultRepository questionnaireResultRepository;

    @Autowired
    EvaluatedQuestionRepository evaluatedQuestionRepository;

    @Transactional
    public Questionnaire findQuestionnaireById(Long id){
        return questionnaireRepository.findQuestionnaireById(id);
    }
    @Transactional
    public List<Questionnaire> findAllQuestionnaires(){
        return questionnaireRepository.findAllQuestionnaires();
    }
    @Transactional
    public QQuestion findQuestionByNumber(Long questionnaire_id, Integer number){
        return questionRepository.findQuestionByNumber(questionnaire_id, number);
    }

    @Transactional
    public void saveQuestionnaire(Questionnaire questionnaire){
        questionnaireRepository.save(questionnaire);
    }
    @Transactional
    public void saveQQuestion(QQuestion question){
        questionRepository.save(question);
    }
    @Transactional
    public List<QQuestion> findAllQuestions(Long id){
        return questionRepository.findAllQuestions(id);
    }
    @Transactional
    public void deleteQuestionnaire(Long id){
        questionnaireRepository.deleteQuestionnaireById(id);
    }
    @Transactional
    public void deleteQQuestionById(Long id){
        questionRepository.deleteQQuestionById(id);
    }
    @Transactional
    public void saveDescription(Description description){
        descriptionRepository.save(description);
    }
    @Transactional
    public void deleteDescriptionById(Long id){
        descriptionRepository.deleteDescriptionById(id);
    }
    @Transactional
    public List<Description> findAllDescriptions(Long id){
        return descriptionRepository.findAllDescriptions(id);
    }
    @Transactional
    public Description findDescriptionById(Long id){
        return descriptionRepository.findDescriptionById(id);
    }
    @Transactional
    public void saveQuestionnaireResult(QuestionnaireResult questionnaireResult){
        questionnaireResultRepository.save(questionnaireResult);
    }
    @Transactional
    public void deleteResultsById(UUID resultId){
        questionnaireResultRepository.deleteQuestionnaireResultById(resultId);
    }
    @Transactional
    public QuestionnaireResult findQuestionnaireResultById(UUID id){
        return questionnaireResultRepository.findQuestionnaireResultById(id);
    }
    @Transactional
    public void saveEvaluatedQuestion(EvaluatedQuestion evaluatedQuestion){
        evaluatedQuestionRepository.save(evaluatedQuestion);
    }
    @Transactional
    public List<EvaluatedQuestion> findAllEvaluatedQuestions(UUID id){
        return evaluatedQuestionRepository.findAllResultsBySurvey(id);
    }
}
