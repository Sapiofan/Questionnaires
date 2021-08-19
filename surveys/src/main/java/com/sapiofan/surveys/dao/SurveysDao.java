package com.sapiofan.surveys.dao;

import com.sapiofan.surveys.entities.Survey;
import com.sapiofan.surveys.entities.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SurveysDao {
    public List<Survey> getSurveys(){
        List<Survey> surveys = new ArrayList<>();
        User user = new User();
        user.setNickname("Sapiofan");
        Survey survey = new Survey("Programming", 20, user);
        surveys.add(survey);
        return surveys;
    }
}
