//package com.sapiofan.surveys.repository.impl;
//
//import com.sapiofan.surveys.repository.SurveyRepository;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//@Repository
//@Transactional
//public class SurveyDaoImpl implements SurveyRepository {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    public List<Survey> findAllSurveys() {
//        Session session = sessionFactory.openSession();
//        String sql = "select s from Survey s";
//        return session.createQuery(sql, Survey.class).getResultList();
//    }
//
//    @Override
//    public List<Question> findAllQuestions(Long survey_id){
//        Session session = sessionFactory.openSession();
//        String sql = "select q from Question s where survey_id = " + survey_id;
//        return session.createQuery(sql, Question.class).getResultList();
//    }
//
//    @Override
//    public List<Survey> findAllSurveys() {
//        return null;
//    }
//
//    @Override
//    public Survey findSurveyByNickName(String nickname) {
//        Session session = sessionFactory.openSession();
//        String sql = "select s from Survey s join User u on s.user_id=u.id where u.nickname = " + nickname;
//        return session.createQuery(sql, Survey.class).getSingleResult();
//    }
//
//    @Override
//    public Survey findBySurveyName(String name) {
//        Session session = sessionFactory.openSession();
//        String sql = "select s from Survey s where s.name = " + name;
//        return session.createQuery(sql, Survey.class).getSingleResult();
//    }
//
//    @Override
//    public void deleteSurvey(Long id) {
//
//    }
//
//    @Override
//    public void save(Survey survey) {
//        Session session = sessionFactory.openSession();
//        session.save(survey);
//    }
//
//
//    @Override
//    public void addQuestion(Question question) {
////        this.hibernateTemplate.save(question);
//    }
//
//    @Override
//    public void deleteQuestion() {
//
//    }
//
//    @Override
//    public Long createSurvey(String name) {
//        Session session = sessionFactory.openSession();
//        Survey survey = new Survey();
//        survey.setName(name);
//        session.save(survey);
//        return survey.getId();
//    }
//
//}
