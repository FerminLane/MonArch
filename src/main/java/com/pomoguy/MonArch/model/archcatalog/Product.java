package com.pomoguy.MonArch.model.archcatalog;


import com.pomoguy.MonArch.model.ModelCommon;
import com.pomoguy.MonArch.model.User;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;

@Entity
@Audited
public class Product extends ModelCommon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    //private String owner;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @NotAudited
    private User author;


    public long getId() {
        return id;
    }

    public void setId(long id) {
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
