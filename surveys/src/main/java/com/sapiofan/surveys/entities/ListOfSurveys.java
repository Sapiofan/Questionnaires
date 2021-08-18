package com.sapiofan.surveys.entities;

import javax.persistence.*;

@Entity
@Table(name = "list")
public class ListOfSurveys {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public  ListOfSurveys(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
