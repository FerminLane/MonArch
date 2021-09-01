package com.pomoguy.MonArch.model;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Calendar;


@MappedSuperclass
public abstract class MonArchCommon {

    protected String name;
    protected Timestamp createDateTime;
    protected Timestamp updateDateTime;
    protected String serviceFields;
    protected String description;
    protected String status;

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
}
