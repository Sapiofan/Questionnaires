package com.sapiofan.surveys.repository;

import com.sapiofan.surveys.entities.Survey;
import com.sapiofan.surveys.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//@Repository
//@Transactional
public interface SurveyRepository extends JpaRepository<Survey, Long> {
    @Query("select s from Survey s")
    List<Survey> findAllSurveys();

    @Query("select u from User u where u.id=1")
    User findUserById();

    @Query("select s from Survey s where s.id=:id")
    Survey findSurveyById(Long id);

//    @Query("select s from Survey s join User u on s.user_id=u.id where u.nickname = :nickname")
//    Survey findSurveyByNickName(String nickname);
//
//    @Query("select s from Survey s where s.name = :name")
//    Survey findBySurveyName(String name);
}
