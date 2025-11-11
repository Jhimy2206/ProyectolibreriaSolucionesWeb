package com.example.demo.controller;

import com.example.demo.entity.Prestamo;
import com.example.demo.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/prestamos")
public class PrestamoController {

    @Autowired
    private PrestamoService prestamoService;

    @GetMapping
    public List<Prestamo> listarTodos() {
        return prestamoService.listarTodos();
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Prestamo> listarPorUsuario(@PathVariable Long usuarioId) {
        return prestamoService.listarPorUsuario(usuarioId);
    }

    @GetMapping("/libro/{libroId}")
    public List<Prestamo> listarPorLibro(@PathVariable Long libroId) {
        return prestamoService.listarPorLibro(libroId);
    }

    @GetMapping("/estado/{estado}")
    public List<Prestamo> listarPorEstado(@PathVariable String estado) {
        return prestamoService.listarPorEstado(estado);
    }

    @PostMapping
    public ResponseEntity<Prestamo> crear(@RequestBody Prestamo prestamo) {
        try {
            Prestamo nuevoPrestamo = prestamoService.crearPrestamo(prestamo);
            return ResponseEntity.ok(nuevoPrestamo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}/devolver")
    public ResponseEntity<Prestamo> devolverLibro(@PathVariable Long id) {
        try {
            Prestamo prestamo = prestamoService.devolverLibro(id);
            return ResponseEntity.ok(prestamo);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> obtenerPorId(@PathVariable Long id) {
        Optional<Prestamo> prestamo = prestamoService.obtenerPorId(id);
        return prestamo.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            prestamoService.eliminar(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
