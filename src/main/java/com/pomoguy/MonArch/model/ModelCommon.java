package com.pomoguy.MonArch.model;


import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;


import javax.persistence.*;


import java.sql.Timestamp;
import java.time.Instant;


@MappedSuperclass
@Audited
public abstract class ModelCommon {

    protected String archCode;

    protected Timestamp createDateTime;

    protected Timestamp updateDateTime;

    protected String updatedBy;

    protected String serviceFields;

    protected String description;

    protected Boolean isActual;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @NotAudited
    protected User author;

    public Timestamp getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime() {
        this.createDateTime = Timestamp.from(Instant.now());
    }

    public Timestamp getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime() {
        this.updateDateTime = Timestamp.from(Instant.now());
    }

    public String getServiceFields() {
        return serviceFields;
    }

    public void setServiceFields(String serviceFields) {
        this.serviceFields = serviceFields;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getArchCode() {
        return archCode;
    }

    public void setArchCode(String archCode) {
        this.archCode = archCode;
    }

    public Boolean getActual() {
        return isActual;
    }

    public void setActual(Boolean actual) {
        isActual = actual;
    }
}

