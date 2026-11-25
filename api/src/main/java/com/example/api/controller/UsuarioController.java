package com.example.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.dto.UsuarioRequestDTO;
import com.example.api.dto.UsuarioResponseDTO;
import com.example.api.entity.Usuario;
import com.example.api.mapper.UsuarioMapper;
import com.example.api.service.UsuarioService;

@RestController 
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired 
    private UsuarioService usuarioService;

    @GetMapping("/{id}")    
    public UsuarioResponseDTO getUsuario(@PathVariable  int id) {
        return usuarioService.getUsuarioRepository(id);
    }

    @PostMapping
    public UsuarioResponseDTO createUsuario(@RequestBody UsuarioRequestDTO usuario) {
        // Lógica para criar um novo usuário
        // Por exemplo, você pode chamar um método do serviço para salvar o usuário no banco de dados
        Usuario createdUsuario = usuarioService.createUsuario(usuario);
        return UsuarioMapper.toResponse(createdUsuario);
    }
}
