package com.example.rest1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "t_reponse")
public class Reponse {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PK_Reponse", length = 50)
    private Integer pk;
    @Column(name = "reponse", length = 50)
    private String reponse;
    @Column(name = "correcte")
    private Boolean correcte;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_question")
    private Question question;

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public Boolean getCorrecte() {
        return correcte;
    }

    public void setCorrecte(Boolean correcte) {
        this.correcte = correcte;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String toString(){
        return question.getEnoncer() + " Reponses: " + reponse + ", "; 
    }
}
