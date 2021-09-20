package com.pomoguy.MonArch.model.committee;

import com.pomoguy.MonArch.dao.committee.CommitteeStatusRepo;
import com.pomoguy.MonArch.model.ModelCommon;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Audited
public class Committee extends ModelCommon {

    @Id
    @GeneratedValue(generator = "monarch-generator")
    @GenericGenerator(name = "monarch-generator",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "comm"),
            strategy = "com.pomoguy.MonArch.generator.MonarchIdGenerator")
    private String id;

    @NotBlank(message = "Обязательно к заполнению")
    private String agenda;
    private String approxDateOfMeeting;
    private String dateOfMeeting;
    private String initialDecision;
    private String finalDecision;

    @NotBlank(message = "Обязательно к заполнению")
    private String applicant;
    private String reviewer;
    private String comment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    private CommitteeStatus status;

    private String type;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public String getDateOfMeeting() {
        return dateOfMeeting;
    }

    public void setDateOfMeeting(String dateOfMeeting) {
        this.dateOfMeeting = dateOfMeeting;
    }

    public CommitteeStatus getStatus() {
        return status;
    }

    public void setStatus(CommitteeStatus status) {
        this.status = status;
    }

    public String getInitialDecision() {
        return initialDecision;
    }

    public void setInitialDecision(String initialDecision) {
        this.initialDecision = initialDecision;
    }

    public String getFinalDecision() {
        return finalDecision;
    }

    public void setFinalDecision(String finalDecision) {
        this.finalDecision = finalDecision;
    }

    public String getApproxDateOfMeeting() {
        return approxDateOfMeeting;
    }

    public void setApproxDateOfMeeting(String approxDateOfMeeting) {
        this.approxDateOfMeeting = approxDateOfMeeting;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Committee() {

    }

    public Committee(String agenda) {
        this.agenda = agenda;
    }
}
