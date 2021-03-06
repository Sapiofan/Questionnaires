package com.sapiofan.surveys.services.survey;

import com.sapiofan.surveys.entities.survey.Answer;
import com.sapiofan.surveys.entities.survey.Survey;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface SurveyService {
    List<Survey> findAllSurveys();
    Survey findSurveyById(Long id);

    List<Survey> findSurveyByNickName(String nickname);
    List<Survey> findBySurveyName(String name);

    Survey createSurvey(Long surveyId, String name, String description, Authentication authentication);

    void save(Survey survey);
    void deleteSurveyById(Long id);

    boolean checkInput(List<Answer> answers);
}
