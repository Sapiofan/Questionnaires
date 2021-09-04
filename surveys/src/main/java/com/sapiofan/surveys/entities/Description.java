package com.sapiofan.surveys.entities;

import javax.persistence.*;

@Entity
@Table(name = "descriptions")
public class Description {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer number;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionnaire_id")
    private Questionnaire questionnaire;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer start_scale;

    @Column(nullable = false)
    private Integer end_scale;

    public Description() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
        questionnaire.addDescription(this);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStart_scale() {
        return start_scale;
    }

    public void setStart_scale(Integer start_scale) {
        this.start_scale = start_scale;
    }

    public Integer getEnd_scale() {
        return end_scale;
    }

    public void setEnd_scale(Integer end_scale) {
        this.end_scale = end_scale;
    }
}
