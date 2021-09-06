package com.sapiofan.surveys.repository.survey;

import com.sapiofan.surveys.entities.survey.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    @Query("select a from Answer a where a.question.id = :questionId")
    List<Answer> findAllAnswers(Long questionId);

    @Query("select a from Answer a where a.id = :answerId")
    Answer findAnswerById(Long answerId);

    @Query("select a from Answer a where a.question.id = :questionId and a.number = :number")
    Answer findAnswerByNumber(Long questionId, Integer number);

    @Query("delete from Answer a where a.id = :id")
    @Modifying
    void deleteAnswerById(Long id);
}
