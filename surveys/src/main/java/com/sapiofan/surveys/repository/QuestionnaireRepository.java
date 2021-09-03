package com.sapiofan.surveys.repository;

import com.sapiofan.surveys.entities.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {
    @Query("select q from Questionnaire q where q.id = :id")
    Questionnaire findQuestionnaireById(Long id);

    @Query("delete from Questionnaire q where q.id = :id")
    @Modifying
    void deleteQuestionnaireById(Long id);
}