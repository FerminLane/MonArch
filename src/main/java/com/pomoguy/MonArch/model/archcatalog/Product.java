package com.pomoguy.MonArch.model.archcatalog;


import com.pomoguy.MonArch.model.ModelCommon;
import com.pomoguy.MonArch.model.User;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;

@Entity
@Audited
public class Product extends ModelCommon {

    @Id
    @GeneratedValue(generator = "monarch-generator")
    @GenericGenerator(name = "monarch-generator",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "p"),
            strategy = "com.pomoguy.MonArch.generator.MonarchIdGenerator")
    private String id;


    //private String owner;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @NotAudited
    private User author;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }




    public Product() {

    }

    public Product(String name, User author, String description) {
        this.name = name;
        this.author = author;
        this.description = description;
    }

}
