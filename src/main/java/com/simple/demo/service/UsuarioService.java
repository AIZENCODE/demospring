package com.simple.demo.service;

import com.simple.demo.model.Usuario;
import com.simple.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import com.simple.demo.exception.UsuarioNoEncontradoException;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario crear(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }


    public Usuario buscarPorId(Long id) {

        return usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new UsuarioNoEncontradoException(id)

                );
    }

    public Usuario actualizar(Long id, Usuario datosNuevos) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new UsuarioNoEncontradoException(id)

                );

        usuario.setNombre(datosNuevos.getNombre());
        usuario.setEmail(datosNuevos.getEmail());

        return usuarioRepository.save(usuario);
    }

    public void eliminar(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() ->
                        new UsuarioNoEncontradoException(id)
                );

        usuarioRepository.delete(usuario);
    }

}