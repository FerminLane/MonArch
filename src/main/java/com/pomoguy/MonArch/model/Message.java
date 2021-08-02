package com.pomoguy.MonArch.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String message;
    private String tag;

    public Message() {

    }


    public Message(String message, String tag) {
        this.message = message;
        this.tag = tag;
    }



    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public String getMessage() {
        return message;
    }

    public Integer getId() {
        return id;
    }
}
