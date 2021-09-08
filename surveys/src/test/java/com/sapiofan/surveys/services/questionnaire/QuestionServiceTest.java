package com.sapiofan.surveys.services.questionnaire;

import com.sapiofan.surveys.entities.questionnaire.QQuestion;
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
public class QuestionServiceTest {

    @Autowired
    private QuestionnaireQuestionsService questionnaireQuestionsService;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionnaireService questionnaireService;

    private static Long questionnaireId;

    private static Long questionId;

    @Test
    @Order(1)
    public void testCreateQuestion() {
        userService.save("TestQuestionnaireQuestions", "test2021");
        User user = userService.findUserByNickname("TestQuestionnaireQuestions");
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setName("Questionnaire");
        questionnaire.setScale(Scale.FIVE);
        questionnaire.setGeneral_description("Description");
        questionnaire.setSize(0);
        questionnaire.setUser(user);
        questionnaireService.saveQuestionnaire(questionnaire);
        questionnaireId = questionnaire.getId();

        QQuestion question = questionnaireQuestionsService.createQQuestion(questionnaireId, "NewQuestion");

        QQuestion savedQuestion = questionnaireQuestionsService.findQuestionByNumber(questionnaireId, 1);

        questionId = question.getId();

        Assertions.assertNotNull(savedQuestion);
        assertThat(question.getName().equals(savedQuestion.getName()));

    }

    @Test
    @Order(2)
    public void testFindQuestionById() {
        QQuestion question = questionnaireQuestionsService.findQuestionByNumber(questionnaireId, 1);
        Assertions.assertNotNull(question);
        assertThat(question.getName().equals("NewQuestion"));
    }

    @Test
    @Order(3)
    public void testFindAllQuestions(){
        Collection<QQuestion> questions = questionnaireQuestionsService.findAllQuestions(questionId);
        assertThat(questions.size() != 0);
    }

    @Test
    @Order(4)
    public void testDeleteQuestionByNumber(){
        questionnaireQuestionsService.deleteQQuestionById(questionnaireId, 1);
        Assertions.assertNull(questionnaireQuestionsService.findQuestionByNumber(questionnaireId, 1));
        userService.deleteUser(userService.findUserByNickname("TestQuestionnaireQuestions"));
    }
}
