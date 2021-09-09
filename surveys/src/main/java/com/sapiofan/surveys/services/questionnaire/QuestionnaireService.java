package com.sapiofan.surveys.services.questionnaire;

import com.sapiofan.surveys.entities.questionnaire.Questionnaire;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface QuestionnaireService {

    Questionnaire findQuestionnaireById(Long id);

    List<Questionnaire> findAllQuestionnaires();

    void saveQuestionnaire(Questionnaire questionnaire);

    Questionnaire createQuestionnaire(Authentication authentication, Long questionnaireId,
                                      String name, String description, Integer scale);

    List<Questionnaire> findByQuestionnaireName(String name);

    List<Questionnaire> findQuestionnaireByNickName(String nickname);

    void deleteQuestionnaire(Long id);

    int maximum(Questionnaire questionnaire);
}
