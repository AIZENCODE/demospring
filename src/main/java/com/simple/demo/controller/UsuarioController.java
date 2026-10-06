package com.simple.demo.controller;

import com.simple.demo.dto.UsuarioRequest;
import com.simple.demo.dto.UsuarioResponse;
import com.simple.demo.mapper.UsuarioMapper;
import com.simple.demo.model.Usuario;
import com.simple.demo.service.UsuarioService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioMapper usuarioMapper;

    public UsuarioController(
            UsuarioService usuarioService,
            UsuarioMapper usuarioMapper) {

        this.usuarioService = usuarioService;
        this.usuarioMapper = usuarioMapper;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> listar() {

        List<UsuarioResponse> respuesta = usuarioService.listar()
                .stream()
                .map(usuarioMapper::toResponse)
                .toList();

        return ResponseEntity.ok(respuesta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> buscarPorId(
            @PathVariable Long id) {

        Usuario usuario = usuarioService.buscarPorId(id);

        return ResponseEntity.ok(
                usuarioMapper.toResponse(usuario)
        );
    }

    @PostMapping
    public ResponseEntity<UsuarioResponse> crear(
            @Valid @RequestBody UsuarioRequest request) {

        Usuario usuario = usuarioMapper.toEntity(request);

        Usuario creado = usuarioService.crear(usuario);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioMapper.toResponse(creado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody UsuarioRequest request) {

        Usuario datosNuevos = usuarioMapper.toEntity(request);

        Usuario actualizado = usuarioService.actualizar(
                id,
                datosNuevos
        );

        return ResponseEntity.ok(
                usuarioMapper.toResponse(actualizado)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        usuarioService.eliminar(id);

        return ResponseEntity.noContent().build();
    }
}