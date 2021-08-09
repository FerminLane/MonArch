package com.pomoguy.MonArch.model.committee;

import com.pomoguy.MonArch.model.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "committe")
public class Committee {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private String id;

    private String agenda;
    private Calendar dateOfMeeting;


    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "committee_id")
    private Set<Question> questions;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Status.class, fetch = FetchType.EAGER)
    private Set<Status> status;

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

    public Calendar getDateOfMeeting() {
        return dateOfMeeting;
    }

    public void setDateOfMeeting(Calendar dateOfMeeting) {
        this.dateOfMeeting = dateOfMeeting;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Set<Status> getStatus() {
        return status;
    }

    public void setStatus(Set<Status> status) {
        this.status = status;
    }
}
