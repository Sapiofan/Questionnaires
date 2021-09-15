package com.sapiofan.surveys.services.questionnaire;

import com.sapiofan.surveys.entities.questionnaire.Questionnaire;
import com.sapiofan.surveys.entities.questionnaire.Scale;
import com.sapiofan.surveys.entities.user.User;
import com.sapiofan.surveys.services.user.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QuestionnaireServiceTest {
    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private UserService userService;

    private static Long questionnaireId;

    @Test
    @Order(1)
    public void testCreateQuestionnaire() {
        userService.save("TestQuestionnaires", "password");

        User user = userService.findUserByNickname("TestQuestionnaires");

        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setName("Questionnaire");
        questionnaire.setNumber(questionnaireService.findAllQuestionnaires().size() + 1);
        questionnaire.setSize(0);
        questionnaire.setUser(user);
        questionnaire.setGeneral_description("description");
        questionnaire.setScale(Scale.FIVE);
        questionnaireService.saveQuestionnaire(questionnaire);

        Questionnaire newQuestionnaire = questionnaireService.findQuestionnaireById(questionnaire.getId());

        questionnaireId = questionnaire.getId();

        assertThat(newQuestionnaire.getName().equals(questionnaire.getName()));

    }

    @Test
    @Order(2)
    public void testFindQuestionnaireById() {
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
        System.out.println("Questionnaire name: " + questionnaire.getName());
        assertThat(questionnaire.getName().equals("Questionnaire"));
    }

    @Test
    @Order(3)
    public void testFindAllQuestionnaires() {
        Collection<Questionnaire> questionnaires = questionnaireService.findAllQuestionnaires();
        assertThat(questionnaires.size() != 0);
    }

    @Test
    @Order(4)
    public void testFindQuestionnaireByName() {
        List<Questionnaire> questionnaires = questionnaireService.findByQuestionnaireName("Questionnaire");
        Assertions.assertTrue(questionnaires.size() == 1 && questionnaires.get(0).getName().equals("Questionnaire"));
    }

    @Test
    @Order(5)
    public void testFindQuestionnaireByUser() {
        List<Questionnaire> questionnaires = questionnaireService.findQuestionnaireByNickName("TestQuestionnaires");
        User user = userService.findUserByNickname("TestQuestionnaires");
        Assertions.assertEquals(1, questionnaires.size());
        Assertions.assertEquals(questionnaires.get(0).getUser().getId(), user.getId());
    }

    @Test
    @Order(6)
    public void testDeleteQuestionnaireById() {
        questionnaireService.deleteQuestionnaire(questionnaireId);
        Assertions.assertNull(questionnaireService.findQuestionnaireById(questionnaireId));
        userService.deleteUser(userService.findUserByNickname("TestQuestionnaires"));
    }

    @Test
    @Order(7)
    public void notCreatedQuestionnaire() {
        Assertions.assertNull(questionnaireService.findQuestionnaireById(0l));
    }
}
