package com.example.demo.repository;

import com.example.demo.model.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    @Query("SELECT d FROM Order d WHERE " +
            "(d.isDeleted = false) AND " +
            "(:keyword IS NULL OR " +
            "d.student.fullname LIKE %:keyword% OR " +
            "d.student.clazz LIKE %:keyword%) AND " +
            "(:uniformGender IS NULL OR d.uniform.gender = :uniformGender) AND " +
            "(:uniformSize IS NULL OR d.uniform.sizeName = :uniformSize) AND " +
            "(:fromDate IS NULL OR d.createdOn >= :fromDate) AND " +
            "(:toDate IS NULL OR d.createdOn <= :toDate) "
    )
    Page<Order> findOrderByCriteria(
            @Param("keyword") String keyword,
            @Param("uniformGender") Boolean uniformGender,
            @Param("uniformSize") String uniformSize,
            @Param("fromDate") Date fromDate,
            @Param("toDate") Date toDate,
            Pageable pageable
    );
}
