package com.sapiofan.surveys.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "questionnaire_results")
public class QuestionnaireResult {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionnaire_id")
    private Questionnaire questionnaire;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private Date start;

    @Column(nullable = false)
    private Date end_time;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "results")
    private List<EvaluatedQuestion> evaluated_questions;

    public QuestionnaireResult(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public List<EvaluatedQuestion> getEvaluated_questions() {
        return evaluated_questions;
    }

    public void setEvaluated_questions(List<EvaluatedQuestion> evaluated_questions) {
        this.evaluated_questions = evaluated_questions;
    }

    public void addEvaluatedQuestion(EvaluatedQuestion evaluatedQuestion){
        this.evaluated_questions.add(evaluatedQuestion);
    }
}
