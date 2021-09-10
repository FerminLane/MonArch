package com.pomoguy.MonArch.model.archcatalog;

import com.pomoguy.MonArch.model.ModelCommon;
import com.pomoguy.MonArch.model.User;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.NotAudited;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Vendor extends ModelCommon {

    @Id
    @GeneratedValue(generator = "monarch-generator")
    @GenericGenerator(name = "monarch-generator",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "ven"),
            strategy = "com.pomoguy.MonArch.generator.MonarchIdGenerator")
    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Vendor() {

    }

    public Vendor(String name, User author) {
        this.name = name;
        this.author = author;
    }

}
