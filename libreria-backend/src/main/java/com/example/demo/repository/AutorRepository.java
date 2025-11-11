package com.example.demo.repository;

import com.example.demo.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    List<Autor> findByNombreContainingOrApellidoContaining(String nombre, String apellido);
    boolean existsByNombreAndApellido(String nombre, String apellido);
}