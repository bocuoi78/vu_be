package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "ORDERS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "IS_DELETED=FALSE")
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", unique = true, nullable = false)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "STUDENT_ID")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UNIFORM_ID")
    private Uniform uniform;

    @Column(name = "PAID")
    private Boolean paid = Boolean.FALSE;

    @Column(name = "TRANSFER")
    private Boolean transfer = Boolean.FALSE;

    @Column(name = "RECEIVED")
    private Boolean received = Boolean.FALSE;
}
