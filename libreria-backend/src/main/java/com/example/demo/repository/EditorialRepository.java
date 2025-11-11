package com.example.demo.repository;

import com.example.demo.entity.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditorialRepository extends JpaRepository<Editorial, Long> {
    boolean existsByNombre(String nombre);
}