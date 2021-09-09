package com.pomoguy.MonArch.model.archcatalog;


import com.pomoguy.MonArch.model.ModelCommon;
import com.pomoguy.MonArch.model.User;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import java.util.Set;

@Entity
@Audited
public class ITSystem extends ModelCommon {

    @Id
    @GeneratedValue(generator = "monarch-generator")
    @GenericGenerator(name = "monarch-generator",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "sys"),
            strategy = "com.pomoguy.MonArch.generator.MonarchIdGenerator")
    private String id;

    private String buildingArea;
    private String passportName;
    private String owner;


    @ManyToMany
    @JoinTable(
            name = "sys_consist_of",
            joinColumns = @JoinColumn(name = "sys_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products;







    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public ITSystem() {

    }

    public ITSystem(String name, User author, String description) {
        this.name = name;
        this.author = author;
        this.description = description;
    }

}
