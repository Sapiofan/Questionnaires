package com.sapiofan.surveys.repository.questionnaire;

import com.sapiofan.surveys.entities.questionnaire.EvaluatedQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface EvaluatedQuestionRepository extends JpaRepository<EvaluatedQuestion, Long> {
    @Query("select eq from EvaluatedQuestion eq where eq.results.id = :id")
    List<EvaluatedQuestion> findAllResultsBySurvey(UUID id);
}
