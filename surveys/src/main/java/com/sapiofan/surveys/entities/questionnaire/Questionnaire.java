package com.sapiofan.surveys.entities.questionnaire;

import com.sapiofan.surveys.entities.ListOfSurveys;
import com.sapiofan.surveys.entities.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "questionnaires")
public class Questionnaire extends ListOfSurveys {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer size;

    private String general_description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Scale scale;

    @OneToMany(mappedBy = "questionnaire", fetch = FetchType.LAZY)
    private List<QQuestion> questions = new ArrayList<>();

    @OneToMany(mappedBy = "questionnaire", fetch = FetchType.LAZY)
    private List<Description> descriptions = new ArrayList<>();


    public Questionnaire() {
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

    public String getGeneral_description() {
        return general_description;
    }

    public void setGeneral_description(String general_description) {
        this.general_description = general_description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        user.addQuestionnaire(this);
    }

    public List<QQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QQuestion> questions) {
        this.questions = questions;
    }

    public List<Description> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<Description> descriptions) {
        this.descriptions = descriptions;
    }

    public Scale getScale() {
        return scale;
    }

    public void setScale(Scale scale) {
        this.scale = scale;
    }

    public void addQuestion(QQuestion question) {
        this.questions.add(question);
        size = questions.size();
    }

    public void addDescription(Description description) {
        this.descriptions.add(description);
    }
}
