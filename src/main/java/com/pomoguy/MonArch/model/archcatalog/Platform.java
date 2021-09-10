package com.pomoguy.MonArch.model.archcatalog;

import com.pomoguy.MonArch.model.ModelCommon;
import com.pomoguy.MonArch.model.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Platform extends ModelCommon {

    @Id
    @GeneratedValue(generator = "monarch-generator")
    @GenericGenerator(name = "monarch-generator",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "pl"),
            strategy = "com.pomoguy.MonArch.generator.MonarchIdGenerator")
    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Platform() {

    }

    public Platform(String name, User author) {
        this.name = name;
        this.author = author;
    }

}
