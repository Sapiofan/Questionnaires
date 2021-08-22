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

    public Survey(){}

    public Survey(String name, Integer size, User user){
        this.name = name;
        this.size = size;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void addQuestion(Question question){
        this.questions.add(question);
        size = questions.size();
    }
}
