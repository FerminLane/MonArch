package com.pomoguy.MonArch.model.committee;


import com.pomoguy.MonArch.model.ModelCommon;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Audited
public class CommitteeStatus extends ModelCommon {

    @Id
    @GeneratedValue(generator = "monarch-generator")
    @GenericGenerator(name = "monarch-generator",
            parameters = @org.hibernate.annotations.Parameter(name = "prefix", value = "cs"),
            strategy = "com.pomoguy.MonArch.generator.MonarchIdGenerator")
    private String id;

    @NotBlank(message = "Обязательно к заполнению")
    private String name;

    @NotBlank(message = "Обязательно к заполнению")
    private String color;

    private Boolean isAvailable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public CommitteeStatus() {

    }

    public CommitteeStatus(String name, String color, Boolean isAvailable) {
        this.name = name;
        this.color = color;
        this.isAvailable = isAvailable;
    }
}
