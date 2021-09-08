package com.sapiofan.surveys.repository.survey;

import com.sapiofan.surveys.entities.survey.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    @Query("select s from Survey s")
    List<Survey> findAllSurveys();

    @Query("select s from Survey s where s.id=:id")
    Survey findSurveyById(Long id);

    @Query("select s from Survey s where s.number = :number")
    Survey findSurveyByNumber(Integer number);

    @Query("delete from Survey s where s.id=:id")
    @Modifying
    void deleteById(Long id);

//    @Query("select s from Survey s join User u on s.user_id=u.id where u.nickname = :nickname")
//    Survey findSurveyByNickName(String nickname);
//
//    @Query("select s from Survey s where s.name = :name")
//    Survey findBySurveyName(String name);
}
