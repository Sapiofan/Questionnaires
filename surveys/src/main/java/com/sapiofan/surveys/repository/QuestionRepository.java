package com.sapiofan.surveys.repository;

import com.sapiofan.surveys.entities.Question;
<<<<<<< HEAD
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
=======
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
>>>>>>> origin/main
import org.springframework.data.repository.query.Param;

import java.util.List;

//@Repository
//@Transactional
<<<<<<< HEAD
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("select q from Question q where q.survey.id = :surveyId")
    List<Question> findAllQuestions(@Param("surveyId") Long surveyId);

    @Query("select q from Question q where q.id=:id")
    Question findQuestionById(Long id);


//    @Query("update Question q set q.description = :description where q.id = :id")
//    @Modifying
//    void updateQuestion(String description, Long id);
//
//    @Query("delete q from Question q where q.id = :id")
//    void deleteQuestion(@Param("id") Long id);
=======
public interface QuestionRepository extends CrudRepository<Question, Long> {
    @Query("select q from Question s where survey_id = :survey_id")
    List<Question> findAllQuestions(@Param("survey_id") Long survey_id);

    @Query("update Question q set q.description = :description where q.id = :id")
    @Modifying
    void updateQuestion(String description, Long id);

    @Query("delete q from Question q where q.id = :id")
    void deleteQuestion(@Param("id") Long id);
>>>>>>> origin/main
}
