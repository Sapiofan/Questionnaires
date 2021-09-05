package com.sapiofan.surveys.repository.questionnaire;

import com.sapiofan.surveys.entities.questionnaire.Description;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DescriptionRepository extends JpaRepository<Description, Long> {
    @Query("select d from Description d where d.questionnaire.id = :id")
    List<Description> findAllDescriptions(Long id);

    @Query("select d from Description d where d.id = :id")
    Description findDescriptionById(Long id);

    @Query("delete from Description d where d.id = :id")
    @Modifying
    void deleteDescriptionById(Long id);
}
