package com.sapiofan.surveys.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "surveys")
public class Survey extends ListOfSurveys {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "survey", fetch = FetchType.LAZY)
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "survey", fetch = FetchType.LAZY)
    private List<SurveyResults> results = new ArrayList<>();

    public Survey(){}

    public String getName() {
        return name;
    }

    public Integer getSize() {
        return size;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        user.addSurvey(this);
    }

    public List<SurveyResults> getResults() {
        return results;
    }

    public void setResults(List<SurveyResults> results) {
        this.results = results;
    }

    public void addQuestion(Question question){
        this.questions.add(question);
        this.size = questions.size();
    }
}
