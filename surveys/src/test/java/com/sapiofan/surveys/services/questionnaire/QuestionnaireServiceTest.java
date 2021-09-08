package com.sapiofan.surveys.services.questionnaire;

import com.sapiofan.surveys.entities.questionnaire.Questionnaire;
import com.sapiofan.surveys.entities.questionnaire.Scale;
import com.sapiofan.surveys.entities.user.User;
import com.sapiofan.surveys.services.user.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Rollback(false)
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
        questionnaire.setSize(0);
        questionnaire.setUser(user);
        questionnaire.setGeneral_description("description");
        questionnaire.setScale(Scale.FIVE);
        questionnaireService.saveQuestionnaire(questionnaire);

        Questionnaire newQuestionnaire = questionnaireService.findQuestionnaireById(questionnaire.getId());

        questionnaireId = questionnaire.getId();

        Assertions.assertNotNull(newQuestionnaire);
        assertThat(newQuestionnaire.getName().equals(questionnaire.getName()));

    }

    @Test
    @Order(2)
    public void testFindQuestionnaireById() {
        Questionnaire questionnaire = questionnaireService.findQuestionnaireById(questionnaireId);
        System.out.println("Questionnaire name: "+questionnaire.getName());
        Assertions.assertNotNull(questionnaire);
        assertThat(questionnaire.getName().equals("Questionnaire"));
    }

    @Test
    @Order(3)
    public void testFindAllQuestionnaires(){
        Collection<Questionnaire> questionnaires = questionnaireService.findAllQuestionnaires();
        assertThat(questionnaires.size() != 0);
    }

    @Test
    @Order(4)
    public void testDeleteSurveyById(){
        questionnaireService.deleteQuestionnaire(questionnaireId);
        Assertions.assertNull(questionnaireService.findQuestionnaireById(questionnaireId));
        userService.deleteUser(userService.findUserByNickname("TestQuestionnaires"));
    }

    @Test
    @Order(5)
    public void notCreatedQuestionnaire(){
        Assertions.assertNull(questionnaireService.findQuestionnaireById(0l));
    }
}
