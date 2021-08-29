package com.sapiofan.surveys.repository;

import com.sapiofan.surveys.entities.RightAnswers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RightAnswersRepository extends JpaRepository<RightAnswers, Long> {
//    @Query("select ra from RightAnswers ra where ra.result.id = :id")
//    List<RightAnswers> findAllResultsBySurvey(UUID id);
}
