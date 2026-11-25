package com.example.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.dto.UsuarioResponseDTO;

@Service 
public class AuthService {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private SenhaService senhaService;

    public String login(String email, String senha) {
        UsuarioResponseDTO usuario = usuarioService.getUsuarioByEmail(email);
        if (usuario == null || !usuario.getStatus()) {
            throw new RuntimeException("Usuário não encontrado ou senha inválida");
        }

        boolean senhaValida = senhaService.verificar(senha, usuario.getSenha());
        if (!senhaValida) {
            throw new RuntimeException("Usuário não encontrado ou senha inválida");
        }

        usuarioService.atualizarUltimoAcesso(usuario.getId());

        return "Login bem-sucedido";
    }

}
