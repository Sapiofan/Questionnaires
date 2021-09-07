package com.sapiofan.surveys.services.survey;

import com.sapiofan.surveys.entities.survey.Survey;
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
public class SurveyServiceTest {

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private UserService userService;

    private Long surveyId;

    @Test
    @Order(1)
    public void testCreateSurvey() {
        userService.save("TestNickname", "test2021");
        User user = userService.findUserByNickname("TestNickname");
        Survey survey = new Survey();
        survey.setName("Survey");
        survey.setSize(0);
        survey.setUser(user);
        surveyService.save(survey);

        Survey newSurvey = surveyService.findSurveyById(survey.getId());

        surveyId = survey.getId();

        Assertions.assertNotNull(newSurvey);
        assertThat(newSurvey.getName().equals(survey.getName()));

    }

    @Test
    @Order(2)
    public void testFindSurveyById() {
        Survey survey = surveyService.findSurveyById(surveyId);
        Assertions.assertNotNull(survey);
        assertThat(survey.getName().equals("Survey"));
    }

    @Test
    @Order(3)
    public void testFindAllSurveys(){
        Collection<Survey> surveys = surveyService.findAllSurveys();
        assertThat(surveys.size() != 0);
    }

    @Test
    @Order(4)
    public void testDeleteSurveyById(){
        surveyService.deleteSurveyById(surveyId);
        Assertions.assertNull(surveyService.findSurveyById(surveyId));
        userService.deleteUser(userService.findUserByNickname("TestNickname"));
    }

    @Test
    @Order(5)
    public void notCreatedSurvey(){
        Assertions.assertNull(surveyService.findSurveyById(0l));
    }
}
