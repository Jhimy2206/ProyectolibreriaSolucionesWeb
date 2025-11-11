package com.example.demo.service;

import com.example.demo.entity.Prestamo;
import com.example.demo.entity.Libro;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.PrestamoRepository;
import com.example.demo.repository.LibroRepository;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Prestamo> listarTodos() {
        return prestamoRepository.findAll();
    }

    public List<Prestamo> listarPorUsuario(Long usuarioId) {
        return prestamoRepository.findByUsuarioId(usuarioId);
    }

    public List<Prestamo> listarPorLibro(Long libroId) {
        return prestamoRepository.findByLibroId(libroId);
    }

    public List<Prestamo> listarPorEstado(String estado) {
        return prestamoRepository.findByEstado(estado);
    }

    public Prestamo crearPrestamo(Prestamo prestamo) {
        Optional<Libro> libro = libroRepository.findById(prestamo.getLibro().getId());
        Optional<Usuario> usuario = usuarioRepository.findById(prestamo.getUsuario().getId());

        if (!libro.isPresent()) {
            throw new RuntimeException("El libro no existe");
        }
        if (!usuario.isPresent()) {
            throw new RuntimeException("El usuario no existe");
        }

        prestamo.setFechaPrestamo(LocalDateTime.now());
        prestamo.setEstado("PRESTADO");
        return prestamoRepository.save(prestamo);
    }

    public Prestamo devolverLibro(Long prestamoId) {
        Optional<Prestamo> prestamo = prestamoRepository.findById(prestamoId);
        if (!prestamo.isPresent()) {
            throw new RuntimeException("El préstamo no existe");
        }

        Prestamo prestamoExistente = prestamo.get();
        prestamoExistente.setEstado("DEVUELTO");
        prestamoExistente.setFechaDevolucion(LocalDateTime.now());

        return prestamoRepository.save(prestamoExistente);
    }

    public Optional<Prestamo> obtenerPorId(Long id) {
        return prestamoRepository.findById(id);
    }

    public void eliminar(Long id) {
        prestamoRepository.deleteById(id);
    }
}
