package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Data
public abstract class BaseEntity implements Serializable {
    @Column(name = "CREATED_BY", length = 100)
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_ON")
    private Date createdOn;

    @Column(name = "UPDATED_BY", length = 100)
    private String updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_ON")
    private Date updatedOn;

    @Column(name = "IS_DELETED")
    private Boolean isDeleted = Boolean.FALSE;

    @PrePersist
    protected void onCreate() {
        createdOn = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedOn = new Date();
    }
}