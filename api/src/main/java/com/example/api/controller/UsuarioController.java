package com.example.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public UsuarioResponseDTO getUsuario (@PathVariable  int id) {
        return usuarioService.getUsuarioById(id);
    }

    @GetMapping("/clinica/{idClinica}")
    public List<UsuarioResponseDTO> getUsuarioByClinica (@PathVariable int idClinica) {
        return usuarioService.getUsuarioByIdClinica(idClinica);
    }

    @PostMapping
    public UsuarioResponseDTO createUsuario(@RequestBody UsuarioRequestDTO usuario) {
        Usuario createdUsuario = usuarioService.createUsuario(usuario);
        return UsuarioMapper.toResponse(createdUsuario);
    }

    @PutMapping("/{id}")
    public UsuarioResponseDTO updateUsuario(@PathVariable int id, @RequestBody UsuarioRequestDTO usuario) {
        Usuario updatedUsuario = usuarioService.updateUsuario(id, usuario);
        return UsuarioMapper.toResponse(updatedUsuario);
    }

    @PatchMapping("/{id}/status")
    public UsuarioResponseDTO updateUsuarioStatus(
            @PathVariable int id,
            @RequestBody boolean status) {

        Usuario updatedUsuario = usuarioService.updateUsuarioStatus(id, status);
        return UsuarioMapper.toResponse(updatedUsuario);
    }

    @DeleteMapping ("/{id}")
    public UsuarioResponseDTO deleteUsuario (@PathVariable int id) {
        return usuarioService.deleteUsuario(id);
    }
}
