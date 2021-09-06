package com.pomoguy.MonArch.model.archcatalog;


import com.pomoguy.MonArch.model.ModelCommon;
import com.pomoguy.MonArch.model.User;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;

@Entity
@Audited
public class ITSystem extends ModelCommon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String buildingArea;
    private String passportName;


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

    public String getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(String buildingArea) {
        this.buildingArea = buildingArea;
    }

    public String getPassportName() {
        return passportName;
    }

    public void setPassportName(String passportName) {
        this.passportName = passportName;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }




    public ITSystem() {

    }

    public ITSystem(String name, User author, String description, String buildingArea) {
        this.name = name;
        this.author = author;
        this.description = description;
        this.buildingArea = buildingArea;
    }

}
