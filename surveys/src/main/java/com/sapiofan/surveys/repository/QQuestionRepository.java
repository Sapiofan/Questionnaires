package com.sapiofan.surveys.repository;

import com.sapiofan.surveys.entities.QQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QQuestionRepository extends JpaRepository<QQuestion, Long> {

    @Query("select q from QQuestion q where q.questionnaire.id = :id")
    List<QQuestion> findAllQuestions(Long id);

    @Modifying
    @Query("delete from QQuestion q where q.id = :id")
    void deleteQQuestionById(Long id);
}