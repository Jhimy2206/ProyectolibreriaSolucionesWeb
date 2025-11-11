package com.example.demo.service;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario registrar(Usuario usuario) {
        if (usuario.getRol() == null || usuario.getRol().isEmpty()) {
            usuario.setRol("USUARIO"); // Valor por defecto si no se asignó
        }
        return usuarioRepository.save(usuario);
    }


    public Optional<Usuario> buscarPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    public boolean validarLogin(String correo, String contraseña) {
        Optional<Usuario> usuario = usuarioRepository.findByCorreo(correo);

        return usuario.isPresent() && usuario.get().getContrasena().equals(contraseña);
    }
}

