package com.simple.demo.mapper;

import com.simple.demo.dto.UsuarioRequest;
import com.simple.demo.dto.UsuarioResponse;
import com.simple.demo.model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public Usuario toEntity(UsuarioRequest request) {

        Usuario usuario = new Usuario();

        usuario.setNombre(request.getNombre());
        usuario.setEmail(request.getEmail());

        return usuario;
    }

    public UsuarioResponse toResponse(Usuario usuario) {

        return new UsuarioResponse(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail()
        );
    }
}