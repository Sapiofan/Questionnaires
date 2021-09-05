package com.sapiofan.surveys.repository.survey;

import com.sapiofan.surveys.entities.survey.SurveyResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface SurveyResultsRepository extends JpaRepository<SurveyResults, Long> {
    @Query("select sr from SurveyResults sr where sr.id=:id")
    SurveyResults findSurveyResultsById(UUID id);

    @Modifying
    @Query("delete from SurveyResults sr where sr.id=:id")
    void deleteResultsById(UUID id);
}
