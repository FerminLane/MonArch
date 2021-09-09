package com.pomoguy.MonArch.model;


import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Calendar;


@MappedSuperclass
@Audited
public abstract class ModelCommon {

    protected String name;
    protected String archCode;
    protected Timestamp createDateTime;
    protected Timestamp updateDateTime;
    protected String updatedBy;
    protected String serviceFields;
    protected String description;
    protected String status;



    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @NotAudited
    protected User author;

    @Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
    protected String version;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}

