package com.example.demo.service;

import com.example.demo.entity.Resena;
import com.example.demo.repository.ResenaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResenaService {

    @Autowired
    private ResenaRepository resenaRepository;

    public List<Resena> listarTodas() {
        return resenaRepository.findAll();
    }

    public List<Resena> listarPorLibro(Long libroId) {
        return resenaRepository.findByLibroId(libroId);
    }

    public List<Resena> listarPorUsuario(Long usuarioId) {
        return resenaRepository.findByUsuarioId(usuarioId);
    }

    public Resena guardar(Resena resena) {
        return resenaRepository.save(resena);
    }

    public Optional<Resena> obtenerPorId(Long id) {
        return resenaRepository.findById(id);
    }

    public void eliminar(Long id) {
        resenaRepository.deleteById(id);
    }
}