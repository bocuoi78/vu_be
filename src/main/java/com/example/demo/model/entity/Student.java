package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "STUDENT")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "IS_DELETED=FALSE")
public class Student extends BaseEntity {
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    private String id;

    @Column(name = "FULLNAME", nullable = false)
    private String fullname;

    @Column(name = "CLAZZ", nullable = false)
    private String clazz;

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTHDAY", nullable = false)
    private Date birthday;

    @Column(name = "GENDER", nullable = false)
    private Boolean gender;

    @Column(name = "PHONE", nullable = false)
    private String phone;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @OneToMany(mappedBy = "student")
    private Set<Order> orderSet = new LinkedHashSet<>();
}
