package com.sapiofan.surveys.entities.user;

import com.sapiofan.surveys.entities.questionnaire.Questionnaire;
import com.sapiofan.surveys.entities.survey.Survey;
import com.sapiofan.surveys.entities.survey.SurveyResults;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Date created_at;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private List<Survey> surveys = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<Questionnaire> questionnaires = new LinkedHashSet<>();

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
    private Set<SurveyResults> results = new LinkedHashSet<>();

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public List<Survey> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<Survey> surveys) {
        this.surveys = surveys;
    }

    public Set<Questionnaire> getQuestionnaires() {
        return questionnaires;
    }

    public void setQuestionnaires(Set<Questionnaire> questionnaires) {
        this.questionnaires = questionnaires;
    }

    public Set<SurveyResults> getResults() {
        return results;
    }

    public void setResults(Set<SurveyResults> results) {
        this.results = results;
    }

    public void addSurvey(Survey survey) {
        this.surveys.add(survey);
    }

    public void addQuestionnaire(Questionnaire questionnaire) {
        this.questionnaires.add(questionnaire);
    }
}
