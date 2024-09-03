package com.example.demo.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "UNIFORM")
@Getter
@Setter
public class Uniform {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ID", unique = true, nullable = false)
    private String id;

    @Column(name = "GENDER", nullable = false)
    private Boolean gender;

    @Column(name = "SIZE_CODE")
    private Long sizeCode;

    @Column(name = "SIZE_NAME", nullable = false, length = 5)
    private String sizeName;

    @Column(name = "QUANTITY_IN_STOCK")
    private Long quantityInStock;

    @OneToMany(mappedBy = "uniform")
    private Set<Order> orderSet = new LinkedHashSet<>();
}
