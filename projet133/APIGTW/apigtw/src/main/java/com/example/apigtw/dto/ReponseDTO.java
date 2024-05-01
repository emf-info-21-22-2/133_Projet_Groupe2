package com.example.apigtw.dto;

public class ReponseDTO {
    private String reponse;
    private boolean correcte;
    private int fk_question;

    // Constructeur par défaut
    public ReponseDTO() {
    }

    // Constructeur avec paramètres
    public ReponseDTO(String reponse, boolean correcte, int fk_question) {
        this.reponse = reponse;
        this.correcte = correcte;
        this.fk_question = fk_question;
    }

    // Getters et setters

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public boolean getCorrecte() {
        return correcte;
    }

    public void setCorrecte(boolean correcte) {
        this.correcte = correcte;
    }

    public int getQuestion() {
        return fk_question;
    }

    public void setQuestion(int fk_question) {
        this.fk_question = fk_question;
    }
}

