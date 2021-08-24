package com.sapiofan.surveys.repository;

import com.sapiofan.surveys.entities.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//@Repository
//@Transactional
public interface SurveyRepository extends JpaRepository<Survey, Long> {
    @Query("select s from Survey s")
    List<Survey> findAllSurveys();

    @Query("select s from Survey s join User u on s.user_id=u.id where u.nickname = :nickname")
    Survey findSurveyByNickName(String nickname);

    @Query("select s from Survey s where s.name = :name")
    Survey findBySurveyName(String name);

//    @Query("delete s from Survey s where s.id = :id")
//    void deleteSurvey(Long id);

//    void save(Survey survey);

}
