package com.sapiofan.surveys.services.questionnaire;

import com.sapiofan.surveys.entities.questionnaire.Questionnaire;
import com.sapiofan.surveys.entities.questionnaire.QuestionnaireResult;
import com.sapiofan.surveys.entities.questionnaire.Scale;
import com.sapiofan.surveys.entities.user.User;
import com.sapiofan.surveys.services.user.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class QuestionnaireResultsServiceTest {

    @Autowired
    private QuestionnaireService questionnaireService;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionnaireResultsService questionnaireResultsService;

    private Long questionnaireId;

    private Long questionId;

    private UUID resultsId;

    @Test
    @Order(1)
    public void testCreateResults() {
        userService.save("TestNickname", "test2021");
        User user = userService.findUserByNickname("TestNickname");
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setName("Questionnaire");
        questionnaire.setSize(0);
        questionnaire.setUser(user);
        questionnaire.setGeneral_description("description");
        questionnaire.setScale(Scale.FIVE);
        questionnaireService.saveQuestionnaire(questionnaire);
        questionnaireId = questionnaire.getId();

        QuestionnaireResult questionnaireResult = new QuestionnaireResult();
        questionnaireResult.setQuestionnaire(questionnaire);
        questionnaireResult.setUser(user);
        Timestamp timestamp = Timestamp.from(Instant.now());
        questionnaireResult.setStart(timestamp);
        questionnaireResult.setEnd_time(timestamp);
        questionnaireResultsService.saveQuestionnaireResult(questionnaireResult);
        resultsId = questionnaireResult.getId();

        QuestionnaireResult newResults = questionnaireResultsService.findQuestionnaireResultById(questionnaireResult.getId());

        Assertions.assertNotNull(newResults);
    }

    @Test
    @Order(2)
    public void testFindResultsById() {
        QuestionnaireResult newResults = questionnaireResultsService.findQuestionnaireResultById(resultsId);
        Assertions.assertNotNull(newResults);
    }


    @Test
    @Order(3)
    public void testDeleteResultsById(){
        questionnaireResultsService.deleteResultsById(resultsId);
        Assertions.assertNull(questionnaireResultsService.findQuestionnaireResultById(resultsId));
        userService.deleteUser(userService.findUserByNickname("TestNickname"));
    }
}
