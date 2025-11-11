package com.example.demo.service;

import com.example.demo.entity.Autor;
import com.example.demo.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public List<Autor> listarTodos() {
        return autorRepository.findAll();
    }

    public Autor guardar(Autor autor) {
        return autorRepository.save(autor);
    }

    public boolean existePorNombreYApellido(String nombre, String apellido) {
        return autorRepository.existsByNombreAndApellido(nombre, apellido);
    }

    public List<Autor> buscarPorNombreOApellido(String q) {
        return autorRepository.findByNombreContainingOrApellidoContaining(q, q);
    }

    public Optional<Autor> obtenerPorId(Long id) {
        return autorRepository.findById(id);
    }

    public Autor actualizar(Long id, Autor autor) {
        if (!autorRepository.existsById(id)) {
            throw new RuntimeException("Autor no encontrado");
        }
        autor.setId(id);
        return autorRepository.save(autor);
    }

    public void eliminar(Long id) {
        autorRepository.deleteById(id);
    }
}