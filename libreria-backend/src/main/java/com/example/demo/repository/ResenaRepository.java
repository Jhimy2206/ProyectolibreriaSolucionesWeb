package com.example.demo.repository;

import com.example.demo.entity.Resena;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ResenaRepository extends JpaRepository<Resena, Long> {
    List<Resena> findByLibroId(Long libroId);
    List<Resena> findByUsuarioId(Long usuarioId);
    boolean existsByUsuarioIdAndLibroId(Long usuarioId, Long libroId);
}