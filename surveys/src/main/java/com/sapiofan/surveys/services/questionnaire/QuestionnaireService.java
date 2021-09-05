package com.sapiofan.surveys.services.questionnaire;

import com.sapiofan.surveys.entities.questionnaire.Questionnaire;

import java.util.List;

public interface QuestionnaireService {

    Questionnaire findQuestionnaireById(Long id);

    List<Questionnaire> findAllQuestionnaires();

    void saveQuestionnaire(Questionnaire questionnaire);

    void deleteQuestionnaire(Long id);
}
