package com.sapiofan.surveys.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "results")
public class SurveyResults {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id")
    private Survey survey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private Date start;

    @Column(nullable = false)
    private Date end_time;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "results")
    private List<RightAnswers> right_answers;

    public SurveyResults() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
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

    public List<RightAnswers> getRight_answers() {
        return right_answers;
    }

    public void setRight_answers(List<RightAnswers> right_answers) {
        this.right_answers = right_answers;
    }

    public void addRightAnswers(RightAnswers rightAnswers) {
        this.right_answers.add(rightAnswers);
    }
}
