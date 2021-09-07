package com.sapiofan.surveys.services.survey;

import com.sapiofan.surveys.entities.survey.Question;
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
public class QuestionServiceTest {

    @Autowired
    private SurveyQuestionService surveyQuestionService;

    @Autowired
    private UserService userService;

    @Autowired
    private SurveyService surveyService;

    private Long surveyId;

    private Long questionId;

    @Test
    @Order(1)
    public void testCreateQuestion() {
        userService.save("TestNickname", "test2021");
        User user = userService.findUserByNickname("TestNickname");
        Survey survey = new Survey();
        survey.setName("Survey");
        survey.setSize(0);
        survey.setUser(user);
        surveyService.save(survey);
        surveyId = survey.getId();

        Question question = surveyQuestionService.createQuestion(0l, surveyId, "NewQuestion");

        Question savedQuestion = surveyQuestionService.findQuestionById(question.getId());

        questionId = question.getId();

        Assertions.assertNotNull(savedQuestion);
        assertThat(question.getDescription().equals(savedQuestion.getDescription()));

    }

    @Test
    @Order(2)
    public void testFindQuestionById() {
        Question question = surveyQuestionService.findQuestionById(questionId);
        Assertions.assertNotNull(question);
        assertThat(question.getDescription().equals("NewQuestion"));
    }

    @Test
    @Order(3)
    public void testFindAllQuestions(){
        Collection<Question> questions = surveyQuestionService.findAllQuestions(surveyId);
        assertThat(questions.size() != 0);
    }

    @Test
    @Order(4)
    public void testDeleteQuestionById(){
        surveyQuestionService.deleteQuestionByNumber(surveyId, 1);
        Assertions.assertNull(surveyQuestionService.findQuestionById(questionId));
        userService.deleteUser(userService.findUserByNickname("TestNickname"));
    }
}
