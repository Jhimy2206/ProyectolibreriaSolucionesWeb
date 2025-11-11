package com.example.demo.controller;

import com.example.demo.entity.Resena;
import com.example.demo.service.ResenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/resenas")
public class ResenaController {

    @Autowired
    private ResenaService resenaService;

    @GetMapping
    public List<Resena> listarTodas() {
        return resenaService.listarTodas();
    }

    @GetMapping("/libro/{libroId}")
    public List<Resena> listarPorLibro(@PathVariable Long libroId) {
        return resenaService.listarPorLibro(libroId);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Resena> listarPorUsuario(@PathVariable Long usuarioId) {
        return resenaService.listarPorUsuario(usuarioId);
    }

    @PostMapping
    public ResponseEntity<Resena> crear(@RequestBody Resena resena) {
        try {
            return ResponseEntity.ok(resenaService.guardar(resena));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Resena> obtenerPorId(@PathVariable Long id) {
        return resenaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        resenaService.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
