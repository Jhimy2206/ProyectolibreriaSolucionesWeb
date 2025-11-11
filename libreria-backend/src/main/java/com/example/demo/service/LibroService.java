package com.example.demo.service;

import com.example.demo.entity.Categoria;
import com.example.demo.entity.Libro;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public class LibroService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Libro> listar() {
        return libroRepository.findAll();
    }

    public Libro guardar(Libro libro) {
        if (libro.getCategoria() == null || libro.getCategoria().getId() == null) {
            throw new RuntimeException("Debe proporcionar una categoría válida");
        }

        Long categoriaId = libro.getCategoria().getId();
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + categoriaId));

        libro.setCategoria(categoria);

        return libroRepository.save(libro);
    }

    public Optional<Libro> obtenerPorId(Long id) {
        return libroRepository.findById(id);
    }

    public void eliminar(Long id) {
        libroRepository.deleteById(id);
    }
    public Libro actualizar(Long id, Libro libroActualizado) {
        Libro libroExistente = libroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Libro no encontrado"));

        libroExistente.setTitulo(libroActualizado.getTitulo());
        libroExistente.setAutor(libroActualizado.getAutor());
        libroExistente.setAnio(libroActualizado.getAnio());

        if (libroActualizado.getCategoria() != null && libroActualizado.getCategoria().getId() != null) {
            Categoria categoria = categoriaRepository.findById(libroActualizado.getCategoria().getId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            libroExistente.setCategoria(categoria);
        }

        return libroRepository.save(libroExistente);
    }

}
