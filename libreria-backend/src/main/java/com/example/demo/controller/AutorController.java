package com.example.demo.controller;

import com.example.demo.entity.Autor;
import com.example.demo.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public List<Autor> listarTodos() {
        return autorService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<Autor> crear(@RequestBody Autor autor) {
        if (autorService.existePorNombreYApellido(autor.getNombre(), autor.getApellido())) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(autorService.guardar(autor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> obtenerPorId(@PathVariable Long id) {
        return autorService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public List<Autor> buscar(@RequestParam String q) {
        return autorService.buscarPorNombreOApellido(q);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> actualizar(@PathVariable Long id, @RequestBody Autor autor) {
        try {
            Autor actualizado = autorService.actualizar(id, autor);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        autorService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}