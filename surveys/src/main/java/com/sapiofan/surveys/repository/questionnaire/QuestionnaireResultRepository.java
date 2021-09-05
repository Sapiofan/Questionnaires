package com.sapiofan.surveys.repository.questionnaire;

import com.sapiofan.surveys.entities.questionnaire.QuestionnaireResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface QuestionnaireResultRepository extends JpaRepository<QuestionnaireResult, UUID> {
    @Query("select qr from QuestionnaireResult qr where qr.id = :id")
    QuestionnaireResult findQuestionnaireResultById(UUID id);

    @Query("delete from QuestionnaireResult qr where qr.id = :id")
    @Modifying
    void deleteQuestionnaireResultById(UUID id);
}
