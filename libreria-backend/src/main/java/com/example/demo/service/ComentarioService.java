package com.example.demo.service;

import com.example.demo.entity.Comentario;
import com.example.demo.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComentarioService {

    @Autowired
    private ComentarioRepository comentarioRepository;

    public List<Comentario> listar() {
        return comentarioRepository.findAll();
    }

    public Comentario guardar(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    public Optional<Comentario> obtenerPorId(Long id) {
        return comentarioRepository.findById(id);
    }

    public void eliminar(Long id) {
        comentarioRepository.deleteById(id);
    }
}
