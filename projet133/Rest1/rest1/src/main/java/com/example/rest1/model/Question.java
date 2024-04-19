package com.example.rest1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_Question")
    private Integer pk;
    
    @Column(name = "Enoncer", length = 250)
    private String enoncer;

    // Getters et Setters
    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public String getEnoncer() {
        return enoncer;
    }

    public void setEnoncer(String enoncer) {
        this.enoncer = enoncer;
    }
}
