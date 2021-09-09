package com.sapiofan.surveys.services.survey;

import com.sapiofan.surveys.entities.survey.Answer;
import com.sapiofan.surveys.entities.survey.Question;
import com.sapiofan.surveys.entities.survey.Survey;
import com.sapiofan.surveys.entities.user.User;
import com.sapiofan.surveys.services.user.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AnswerServiceTest {
    @Autowired
    private SurveyQuestionService surveyQuestionService;

    @Autowired
    private UserService userService;

    @Autowired
    private SurveyService surveyService;

    @Autowired
    private AnswersService answersService;

    private static Long surveyId;

    private static Long questionId;

    private static Long answerId;

    @Test
    @Order(1)
    public void testCreateResults() {
        userService.save("TestAnswers", "test2021");
        User user = userService.findUserByNickname("TestAnswers");
        Survey survey = new Survey();
        survey.setName("Survey");
        survey.setSize(0);
        survey.setUser(user);
        survey.setDescription("Description");
        survey.setNumber(1);
        surveyService.save(survey);
        surveyId = survey.getId();

        Question question = surveyQuestionService.createQuestion(0l, surveyId, "NewQuestion");

        questionId = question.getId();

        Answer answer = answersService.createAnswer(question, "Answer", "1");
        answerId = answer.getId();

        Answer savedAnswer = answersService.findAnswerById(answerId);


        Assertions.assertNotNull(savedAnswer);
        assertThat(answer.getAnswer().equals(savedAnswer.getAnswer()));

    }

    @Test
    @Order(2)
    public void testFindAnswerById() {
        Answer answer = answersService.findAnswerById(answerId);
        Assertions.assertNotNull(answer);
        assertThat(answer.getAnswer().equals("Answer"));
    }

    @Test
    @Order(3)
    public void testFindAnswerByNumber() {
        Answer answer = answersService.findAnswerByNumber(questionId, 1);
        Assertions.assertNotNull(answer);
        assertThat(answer.getAnswer().equals("Answer"));
    }

    @Test
    @Order(4)
    public void testFindAllAnswers(){
        Collection<Answer> answers = answersService.findAllAnswers(questionId);
        assertThat(answers.size() != 0);
    }

    @Test
    @Order(5)
    public void testUpdateAnswer(){
        Answer answer = answersService.updateAnswer(answerId, "UPDATED", "1", 1, questionId);
        Assertions.assertNotNull(answer);
        assertThat(answer.getAnswer().equals("UPDATED"));
    }

    @Test
    @Order(6)
    public void testDeleteAnswerById(){
        answersService.deleteAnswerByNumber(questionId, 1);
        Assertions.assertNull(answersService.findAnswerById(answerId));
        userService.deleteUser(userService.findUserByNickname("TestAnswers"));
    }
}
