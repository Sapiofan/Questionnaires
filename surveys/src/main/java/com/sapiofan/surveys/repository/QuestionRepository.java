package com.sapiofan.surveys.repository;

import com.sapiofan.surveys.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//@Repository
//@Transactional
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("select q from Question q where q.survey.id = :surveyId")
    List<Question> findAllQuestions(@Param("surveyId") Long surveyId);

    @Query("select q from Question q where q.id=:id")
    Question findQuestionById(Long id);

    @Query("delete from Question q where q.id = :id")
    @Modifying
    void deleteQuestion(Long id);
}
