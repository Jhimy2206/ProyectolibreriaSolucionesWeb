package com.example.demo.controller;

import com.example.demo.entity.Libro;
import com.example.demo.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/libros")

public class LibroController {

    @Autowired
    private LibroService libroService;

    @GetMapping
    public List<Libro> listar() {
        return libroService.listar();
    }

    @PostMapping
    public Libro guardar(@RequestBody Libro libro) {
        System.out.println("Recibido libro: " + libro.getTitulo() + ", categoría ID: " +
                (libro.getCategoria() != null ? libro.getCategoria().getId() : "null"));
        return libroService.guardar(libro);
    }

    @GetMapping("/{id}")
    public Optional<Libro> obtenerPorId(@PathVariable Long id) {
        return libroService.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Libro> actualizar(@PathVariable Long id, @RequestBody Libro libroActualizado) {
        Optional<Libro> libroExistente = libroService.obtenerPorId(id);

        if (!libroExistente.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Libro libro = libroExistente.get();
        libro.setTitulo(libroActualizado.getTitulo());
        libro.setAutor(libroActualizado.getAutor());
        libro.setAnio(libroActualizado.getAnio());
        libro.setCategoria(libroActualizado.getCategoria());

        Libro libroGuardado = libroService.guardar(libro);
        return ResponseEntity.ok(libroGuardado);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        libroService.eliminar(id);
    }
}