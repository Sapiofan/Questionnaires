package com.sapiofan.surveys.entities;

import javax.persistence.*;

@MappedSuperclass
public class ListOfSurveys {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public ListOfSurveys() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
