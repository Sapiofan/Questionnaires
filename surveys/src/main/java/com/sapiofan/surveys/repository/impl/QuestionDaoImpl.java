//package com.sapiofan.surveys.repository.impl;
//
//import com.sapiofan.surveys.entities.Question;
//import com.sapiofan.surveys.repository.QuestionRepository;
//import org.hibernate.Session;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.PersistenceContext;
//import java.util.List;
//
////package com.sapiofan.surveys.repository.impl;
////
////import com.sapiofan.surveys.entities.Question;
////import com.sapiofan.surveys.repository.QuestionRepository;
////import org.hibernate.Session;
////import org.hibernate.SessionFactory;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Repository;
////
////import javax.persistence.EntityManager;
////import javax.persistence.PersistenceContext;
////import java.util.List;
////
//@Repository
//public class QuestionDaoImpl implements QuestionRepository {
//
//    @PersistenceContext
//    private Session session;
//
//    @Override
//    public List<Question> findAllQuestions(Long survey_id){
////        Session session = sessionFactory.openSession();
//        String sql = "select q from Question s where survey_id = " + survey_id;
//        return session.createQuery(sql, Question.class).getResultList();
//    }
//
//    @Override
//    public void updateQuestion(String description, Long id) {
//
//    }
//
//    @Override
//    public void deleteQuestion(Long id) {
//
//    }
//}
