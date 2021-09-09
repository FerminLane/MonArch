package com.pomoguy.MonArch.model.archcatalog;


import com.pomoguy.MonArch.model.ModelCommon;
import com.pomoguy.MonArch.model.User;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.boot.web.servlet.server.Session;

import javax.persistence.*;
import javax.servlet.http.HttpSession;
import java.util.Set;

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

    //private boolean isActual;

    @ManyToMany(mappedBy = "products")
    @NotAudited
    Set<ITSystem> itSystems;

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

    public Product(String name, User author,String description) {
        this.name = name;
        this.author = author;
        this.description = description;
    }


}
