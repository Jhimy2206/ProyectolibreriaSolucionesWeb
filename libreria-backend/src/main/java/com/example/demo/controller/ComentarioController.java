package com.example.demo.controller;

import com.example.demo.entity.Comentario;
import com.example.demo.service.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioService comentarioService;

    @GetMapping
    public List<Comentario> listar() {
        return comentarioService.listar();
    }

    @PostMapping
    public Comentario guardar(@RequestBody Comentario comentario) {
        return comentarioService.guardar(comentario);
    }

    @GetMapping("/{id}")
    public Optional<Comentario> obtenerPorId(@PathVariable Long id) {
        return comentarioService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        comentarioService.eliminar(id);
    }
}
