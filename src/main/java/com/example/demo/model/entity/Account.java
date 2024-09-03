package com.example.demo.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "ACCOUNT")
@Getter
@Setter
@Where(clause = "IS_DELETED=FALSE")
public class Account extends BaseEntity {
    @Id
    @Column(name = "USERNAME", unique = true, nullable = false)
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FULLNAME", nullable = false)
    private String fullname;
}
