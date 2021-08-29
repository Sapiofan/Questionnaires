package com.sapiofan.surveys.entities;

import javax.persistence.*;

@Entity
@Table(name = "right_answers")
public class RightAnswers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "result_id")
    private SurveyResults results;

    @OneToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @Column(nullable = false)
    private Boolean aBoolean;


    public RightAnswers(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SurveyResults getResults() {
        return results;
    }

    public void setResults(SurveyResults results) {
        this.results = results;
        results.addRightAnswers(this);
    }

    public void addQuestion(Question question, Boolean b){
        setQuestion(question);
        setaBoolean(b);
    }

    public Question getQuestion() {
        return question;
    }

    public Boolean getaBoolean() {
        return aBoolean;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void setaBoolean(Boolean aBoolean) {
        this.aBoolean = aBoolean;
    }
}
