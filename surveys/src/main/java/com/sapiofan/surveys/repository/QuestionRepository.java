package com.sapiofan.surveys.repository;

import com.sapiofan.surveys.entities.Question;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

//@Repository
//@Transactional
public interface QuestionRepository extends CrudRepository<Question, Long> {
    @Query("select q from Question s where survey_id = :survey_id")
    List<Question> findAllQuestions(@Param("survey_id") Long survey_id);

    @Query("update Question q set q.description = :description where q.id = :id")
    @Modifying
    void updateQuestion(String description, Long id);

    @Query("delete q from Question q where q.id = :id")
    void deleteQuestion(@Param("id") Long id);
}
