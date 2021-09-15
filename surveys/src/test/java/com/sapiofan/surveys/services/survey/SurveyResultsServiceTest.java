package com.sapiofan.surveys.services.survey;

import com.sapiofan.surveys.entities.survey.Survey;
import com.sapiofan.surveys.entities.survey.SurveyResults;
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
public class SurveyResultsServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private SurveyResultsService surveyResultsService;

    private static UUID resultsId;

    @Test
    @Order(1)
    public void testCreateResults() {
        userService.save("TestSResults", "test2021");
        User user = userService.findUserByNickname("TestSResults");
        Survey survey = new Survey();
        survey.setName("Survey");
        survey.setSize(0);
        survey.setUser(user);
        survey.setDescription("Description");
        survey.setNumber(1);
        surveyService.save(survey);

        SurveyResults surveyResults = new SurveyResults();
        surveyResults.setSurvey(survey);
        surveyResults.setUser(user);
        Timestamp timestamp = Timestamp.from(Instant.now());
        surveyResults.setStart(timestamp);
        surveyResults.setEnd_time(timestamp);
        surveyResultsService.saveSurveyResults(surveyResults);
        resultsId = surveyResults.getId();

        SurveyResults newResults = surveyResultsService.findSurveyResultsById(surveyResults.getId());


        Assertions.assertNotNull(newResults);

    }

    @Test
    @Order(2)
    public void testFindResultsById() {
        SurveyResults newResults = surveyResultsService.findSurveyResultsById(resultsId);
        Assertions.assertNotNull(newResults);
    }


    @Test
    @Order(3)
    public void testDeleteResultsById() {
        surveyResultsService.deleteResultsById(resultsId);
        Assertions.assertNull(surveyResultsService.findSurveyResultsById(resultsId));
        userService.deleteUser(userService.findUserByNickname("TestSResults"));
    }
}
