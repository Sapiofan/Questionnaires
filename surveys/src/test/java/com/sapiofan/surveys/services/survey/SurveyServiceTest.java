package com.sapiofan.surveys.services.survey;

import com.sapiofan.surveys.entities.survey.Survey;
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
public class SurveyServiceTest {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private UserService userService;

    private static Long surveyId;

    @Test
    @Order(1)
    public void testCreateSurvey() {
        userService.save("TestSurveyUser", "test2021");
        User user = userService.findUserByNickname("TestSurveyUser");
        Survey survey = new Survey();
        survey.setName("TestSurvey");
        survey.setSize(0);
        survey.setUser(user);
        survey.setDescription("Description");
        survey.setNumber(surveyService.findAllSurveys().size() + 1);
        surveyService.save(survey);

        Survey newSurvey = surveyService.findSurveyById(survey.getId());

        surveyId = survey.getId();

        Assertions.assertNotNull(newSurvey);
        assertThat(newSurvey.getName().equals(survey.getName()));

    }

    @Test
    @Order(2)
    public void testFindSurveyById() {
        System.out.println(surveyId);
        Survey survey = surveyService.findSurveyById(surveyId);
        Assertions.assertNotNull(survey);
        System.out.println("Survey name: " + survey.getName());
        assertThat(survey.getName().equals("TestSurvey"));
    }

    @Test
    @Order(3)
    public void testFindAllSurveys() {
        Collection<Survey> surveys = surveyService.findAllSurveys();
        assertThat(surveys.size() != 0);
    }

    @Test
    @Order(4)
    public void testFindSurveyByName() {
        List<Survey> surveys = surveyService.findBySurveyName("TestSurvey");
        Assertions.assertTrue(surveys.size() == 1 && surveys.get(0).getName().equals("TestSurvey"));
    }

    @Test
    @Order(5)
    public void testFindSurveyByUser() {
        List<Survey> surveys = surveyService.findSurveyByNickName("TestSurveyUser");
        User user = userService.findUserByNickname("TestSurveyUser");
        Assertions.assertEquals(1, surveys.size());
        Assertions.assertEquals(surveys.get(0).getUser().getId(), user.getId());
    }

    @Test
    @Order(6)
    public void testDeleteSurveyById() {
        surveyService.deleteSurveyById(surveyId);
        Assertions.assertNull(surveyService.findSurveyById(surveyId));
        userService.deleteUser(userService.findUserByNickname("TestSurveyUser"));
    }

    @Test
    @Order(7)
    public void notCreatedSurvey() {
        Assertions.assertNull(surveyService.findSurveyById(0l));
    }
}
