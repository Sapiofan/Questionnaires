package com.sapiofan.surveys.questionnaire;

import com.sapiofan.surveys.entities.questionnaire.Questionnaire;
import com.sapiofan.surveys.entities.questionnaire.Scale;
import com.sapiofan.surveys.entities.user.User;
import com.sapiofan.surveys.repository.questionnaire.QuestionnaireRepository;
import com.sapiofan.surveys.repository.user.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QuestionnaireRepositoryTest {
    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @Autowired
    private UserRepository userRepository;

    private Long questionnaireId;

    @Test
    @Order(1)
    public void testCreateQuestionnaire() {
        User user = new User();
        user.setNickname("TestNickname");
        user.setPassword("test2021");
        Timestamp timestamp = Timestamp.from(Instant.now());
        user.setCreated_at(timestamp);

        userRepository.save(user);

        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setName("Questionnaire");
        questionnaire.setSize(0);
        questionnaire.setUser(user);
        questionnaire.setGeneral_description("description");
        questionnaire.setScale(Scale.FIVE);
        questionnaireRepository.save(questionnaire);

        Questionnaire newQuestionnaire = questionnaireRepository.findQuestionnaireById(questionnaire.getId());

        questionnaireId = questionnaire.getId();
        System.out.println(questionnaireId);

        assertThat(newQuestionnaire != null && newQuestionnaire.getName().equals(questionnaire.getName()));

    }

    @Test
    @Order(2)
    public void testFindQuestionnaireById() {
        Questionnaire questionnaire = questionnaireRepository.findQuestionnaireById(questionnaireId);
        assertThat(questionnaire != null && questionnaire.getName().equals("Questionnaire"));
    }

    @Test
    @Order(3)
    public void testFindAllQuestionnaires(){
        Collection<Questionnaire> questionnaires = questionnaireRepository.findAllQuestionnaires();
        assertThat(questionnaires.size() != 0);
    }

    @Test
    @Order(4)
    public void testDeleteSurveyById(){
        questionnaireRepository.deleteQuestionnaireById(questionnaireId);
        assertThat(questionnaireRepository.findQuestionnaireById(questionnaireId) == null);
        userRepository.delete(userRepository.findByNickname("TestNickname"));
    }

    @Test
    @Order(5)
    public void notCreatedQuestionnaire(){
        Assertions.assertNull(questionnaireRepository.findQuestionnaireById(0l));
    }
}
