package com.sapiofan.surveys.entities.survey;

import com.sapiofan.surveys.entities.ListOfSurveys;
import com.sapiofan.surveys.entities.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "surveys")
public class Survey extends ListOfSurveys {

    @Column(nullable = false)
    private Integer number;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false)
    private Integer size;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "survey", fetch = FetchType.EAGER)
    private List<Question> questions = new ArrayList<>();

    public Survey() {
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSize() {
        return size;
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

    public void addQuestion(Question question) {
        this.questions.add(question);
        size = questions.size();
    }
}
