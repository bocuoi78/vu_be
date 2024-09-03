package com.example.demo.repository;

import com.example.demo.model.entity.Uniform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UniformRepository extends JpaRepository<Uniform, String> {
    Optional<Uniform> findByGenderAndAndSizeName(Boolean gender, String sizeName);
}
