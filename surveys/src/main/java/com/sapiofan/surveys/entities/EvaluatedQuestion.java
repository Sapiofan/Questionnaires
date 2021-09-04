package com.sapiofan.surveys.entities;

import javax.persistence.*;

@Entity
@Table(name = "evaluated_questions")
public class EvaluatedQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "result_id")
    private QuestionnaireResult results;

    @OneToOne
    @JoinColumn(name = "question_id")
    private QQuestion question;

    @Column(nullable = false)
    private Integer grade;

    public EvaluatedQuestion(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionnaireResult getResults() {
        return results;
    }

    public void setResults(QuestionnaireResult results) {
        this.results = results;
        results.addEvaluatedQuestion(this);
    }

    public QQuestion getQuestion() {
        return question;
    }

    public void setQuestion(QQuestion question) {
        this.question = question;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
