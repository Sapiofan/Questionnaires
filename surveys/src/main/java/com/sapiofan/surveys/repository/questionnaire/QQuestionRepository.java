package com.sapiofan.surveys.repository.questionnaire;

import com.sapiofan.surveys.entities.questionnaire.QQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QQuestionRepository extends JpaRepository<QQuestion, Long> {

    @Query("select q from QQuestion q where q.questionnaire.id = :id")
    List<QQuestion> findAllQuestions(Long id);

    @Query("select q from QQuestion q where q.questionnaire.id = :questionnaireId and q.number = :number")
    QQuestion findQuestionByNumber(Long questionnaireId, Integer number);

    @Query("select q from QQuestion q where q.id = :id")
    QQuestion findQuestionById(Long id);

    @Modifying
    @Query("delete from QQuestion q where q.questionnaire.id = :id and q.number = :number")
    void deleteQQuestionByNumber(Long id, Integer number);

    @Modifying
    @Query("delete from QQuestion q where q.id = :id")
    void deleteQQuestionById(Long id);
}
