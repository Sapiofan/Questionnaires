package com.sapiofan.surveys.repository;

import com.sapiofan.surveys.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query("select a from Answer a where a.question.id = :questionId")
    List<Answer> findAllAnswers(Long questionId);

    @Query("select a from Answer a where a.id = :answerId")
    Answer findAnswerById(Long answerId);

    @Query("delete from Answer a where a.id = :id")
    @Modifying
    void deleteAnswerById(@Param("id") Long id);
}