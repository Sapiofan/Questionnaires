package com.sapiofan.surveys.services.questionnaire;

import com.sapiofan.surveys.entities.questionnaire.Description;
import com.sapiofan.surveys.entities.questionnaire.Questionnaire;
import com.sapiofan.surveys.entities.questionnaire.Scale;
import com.sapiofan.surveys.entities.user.User;
import com.sapiofan.surveys.services.user.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DescriptionServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private DescriptionService descriptionService;

    private static Long questionnaireId;

    private static Long descriptionId;

    @Test
    @Order(1)
    public void testCreateDescription() {
        userService.save("TestDescriptions", "test2021");
        User user = userService.findUserByNickname("TestDescriptions");
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setName("Questionnaire");
        questionnaire.setScale(Scale.FIVE);
        questionnaire.setGeneral_description("Description");
        questionnaire.setSize(0);
        questionnaire.setUser(user);
        questionnaireService.saveQuestionnaire(questionnaire);
        questionnaireId = questionnaire.getId();

        Description description = descriptionService.createDescription("Description", questionnaire, 1, 10);

        Description savedDescription = descriptionService.findDescriptionByNumber(questionnaireId, 1);

        descriptionId = description.getId();

        Assertions.assertNotNull(savedDescription);
        assertThat(description.getDescription().equals(savedDescription.getDescription()));

    }

    @Test
    @Order(2)
    public void testFindDescriptionById() {
        Description description = descriptionService.findDescriptionById(descriptionId);
        System.out.println("Description name: " + description.getDescription());
        Assertions.assertNotNull(description);
        assertThat(description.getDescription().equals("Description"));
    }

    @Test
    @Order(3)
    public void testFindDescriptionByNumber() {
        Description description = descriptionService.findDescriptionByNumber(questionnaireId, 1);
        Assertions.assertNotNull(description);
        assertThat(description.getDescription().equals("Description"));
    }

    @Test
    @Order(4)
    public void testFindAllDescriptions(){
        Collection<Description> descriptions = descriptionService.findAllDescriptions(questionnaireId);
        assertThat(descriptions.size() != 0);
    }

    @Test
    @Order(5)
    public void testDeleteDescriptionByNumber(){
        descriptionService.deleteDescriptionById(descriptionService.findDescriptionById(descriptionId),
                questionnaireService.findQuestionnaireById(questionnaireId));
        Assertions.assertNull(descriptionService.findDescriptionById(descriptionId));
        userService.deleteUser(userService.findUserByNickname("TestDescriptions"));
    }
}
