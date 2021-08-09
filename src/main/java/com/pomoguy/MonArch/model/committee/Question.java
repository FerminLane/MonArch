package com.pomoguy.MonArch.model.committee;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    private String initialForm;
    private String finalForm;
    private Boolean decision;
    private String comment;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInitialForm() {
        return initialForm;
    }

    public void setInitialForm(String initialForm) {
        this.initialForm = initialForm;
    }

    public String getFinalForm() {
        return finalForm;
    }

    public void setFinalForm(String finalForm) {
        this.finalForm = finalForm;
    }

    public Boolean getDecision() {
        return decision;
    }

    public void setDecision(Boolean decision) {
        this.decision = decision;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
