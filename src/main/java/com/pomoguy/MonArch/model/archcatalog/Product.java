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

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Entity
@Audited
public class Product extends ModelCommon {

    @Id
    @GeneratedValue(generator = "monarch-generator")
    @GenericGenerator(name = "monarch-generator",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "p"),
            strategy = "com.pomoguy.MonArch.generator.MonarchIdGenerator")
    private String id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "vendor_id")
    @Audited(targetAuditMode = NOT_AUDITED)
    private Vendor vendor;

    private String platform;
    private Integer moduleCode;



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

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Integer getModuleCode() {
        return moduleCode;
    }

    public void setModuleCode(Integer moduleCode) {
        this.moduleCode = moduleCode;
    }

    public Set<ITSystem> getItSystems() {
        return itSystems;
    }

    public void setItSystems(Set<ITSystem> itSystems) {
        this.itSystems = itSystems;
    }

    public Product() {

    }

    public Product(String name,String version, User author,String description) {
        this.name = name;
        this.version = version;
        this.author = author;
        this.description = description;
    }


}
