package com.sapiofan.surveys.repository.survey;

import com.sapiofan.surveys.entities.survey.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//@Repository
//@Transactional
public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("select q from Question q where q.survey.id = :surveyId")
    List<Question> findAllQuestions(Long surveyId);

    @Query("select q from Question q where q.survey.id = :surveyId and q.number = :number")
    Question findQuestionByNumber(Long surveyId, Integer number);

    @Query("select q from Question q where q.id=:id")
    Question findQuestionById(Long id);

    @Query("delete from Question q where q.id = :id")
    @Modifying
    void deleteQuestion(Long id);
}
