package com.sapiofan.surveys.dao.impl;

import com.sapiofan.surveys.dao.SurveyDao;
import com.sapiofan.surveys.entities.Question;
import com.sapiofan.surveys.entities.Survey;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class SurveyDaoImpl implements SurveyDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Survey> findAllSurveys() {
        Session session = sessionFactory.openSession();
        String sql = "select s from Survey s";
        return session.createQuery(sql, Survey.class).getResultList();
    }

    @Override
    public List<Question> findAllQuestions(Long survey_id){
        Session session = sessionFactory.openSession();
        String sql = "select q from Question s where survey_id = " + survey_id;
        return session.createQuery(sql, Question.class).getResultList();
    }

    @Override
    public Survey findSurveyByNickName(String nickname) {
        Session session = sessionFactory.openSession();
        String sql = "select s from Survey s join User u on s.user_id=u.id where u.nickname = " + nickname;
        return session.createQuery(sql, Survey.class).getSingleResult();
    }

    @Override
    public Survey findBySurveyName(String name) {
        Session session = sessionFactory.openSession();
        String sql = "select s from Survey s where s.name = " + name;
        return session.createQuery(sql, Survey.class).getSingleResult();
    }




    @Override
    public void addQuestion(Question question) {
//        this.hibernateTemplate.save(question);
    }

    @Override
    public void deleteQuestion() {

    }

    @Override
    public Long createSurvey(String name) {
        Session session = sessionFactory.openSession();
        Survey survey = new Survey();
        survey.setName(name);
        session.save(survey);
        return survey.getId();
    }

}
