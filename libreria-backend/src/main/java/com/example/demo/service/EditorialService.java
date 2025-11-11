package com.example.demo.service;

import com.example.demo.entity.Editorial;
import com.example.demo.repository.EditorialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EditorialService {

    @Autowired
    private EditorialRepository editorialRepository;

    public List<Editorial> listarTodas() {
        return editorialRepository.findAll();
    }

    public Editorial guardar(Editorial editorial) {
        return editorialRepository.save(editorial);
    }

    public Optional<Editorial> obtenerPorId(Long id) {
        return editorialRepository.findById(id);
    }

    public Editorial actualizar(Long id, Editorial editorial) {
        if (editorialRepository.existsById(id)) {
            editorial.setId(id);
            return editorialRepository.save(editorial);
        }
        throw new RuntimeException("Editorial no encontrada");
    }

    public void eliminar(Long id) {
        editorialRepository.deleteById(id);
    }

    public boolean existePorNombre(String nombre) {
        return editorialRepository.existsByNombre(nombre);
    }
}