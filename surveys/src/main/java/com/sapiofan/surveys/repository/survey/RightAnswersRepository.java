package com.sapiofan.surveys.repository.survey;

import com.sapiofan.surveys.entities.survey.RightAnswers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface RightAnswersRepository extends JpaRepository<RightAnswers, Long> {
    @Query("select ra from RightAnswers ra where ra.results.id = :id")
    List<RightAnswers> findAllResultsBySurvey(UUID id);

}
