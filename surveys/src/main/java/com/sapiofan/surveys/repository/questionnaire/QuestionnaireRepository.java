package com.sapiofan.surveys.repository.questionnaire;

import com.sapiofan.surveys.entities.questionnaire.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {
    @Query("select q from Questionnaire q where q.id = :id")
    Questionnaire findQuestionnaireById(Long id);

    @Query("select q from Questionnaire q where q.number = :number")
    Questionnaire findQuestionnaireByNumber(Integer number);

    @Query("select q from Questionnaire q")
    List<Questionnaire> findAllQuestionnaires();

    @Query("select q from Questionnaire q where q.user.nickname = :nickname")
    List<Questionnaire> findQuestionnaireByNickName(String nickname);

    @Query("select q from Questionnaire q where q.name = :name")
    List<Questionnaire> findByQuestionnaireName(String name);

    @Query("delete from Questionnaire q where q.id = :id")
    @Modifying
    void deleteQuestionnaireById(Long id);
}
