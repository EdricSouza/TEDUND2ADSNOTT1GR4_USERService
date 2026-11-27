package com.example.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import java.util.NoSuchElementException;
import java.util.List;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;

import com.example.api.dto.LoginResponseDTO;
import com.example.api.dto.UsuarioResponseDTO;

@Service 
public class AuthService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PerfilService perfilService;

    @Autowired
    private SenhaService senhaService;

    @Autowired
    private JwtService jwtService;

    @Value("${app.auth.jwt-validade-minutos:60}")
    private Integer tempoValidade;

    public LoginResponseDTO login(String email, String senha) {
        UsuarioResponseDTO usuario;
        try {
            usuario = usuarioService.getUsuarioByEmail(email);
        } catch (NoSuchElementException exception) {
            throw credenciaisInvalidas();
        }
        if (usuario == null || !usuario.getStatus()) {
            throw credenciaisInvalidas();
        }

        boolean senhaValida = senhaService.verificar(senha, usuario.getSenha());
        if (!senhaValida) {
            throw credenciaisInvalidas();
        }

        Long id = usuario.getId();
        usuarioService.atualizarUltimoAcesso(id.intValue());

        var perfil = usuario.getPerfilId() == null ? null : perfilService.getPerfilById(usuario.getPerfilId());

        LoginResponseDTO resposta = new LoginResponseDTO();
        resposta.setToken(jwtService.emitir(id));
        resposta.setNome(usuario.getNome());
        resposta.setPerfilId(usuario.getPerfilId());
        resposta.setPerfilNome(perfil == null ? null : perfil.getNomePerfil());
        resposta.setTempoValidade(tempoValidade);

        return resposta;
    }

    private static ResponseStatusException credenciaisInvalidas() {
        return new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Usuário não encontrado ou senha inválida");
    }

    @Transactional(readOnly = true)
    public JwtAuthenticationToken autenticarToken(Jwt jwt) {
        int id;
        try {
            id = Integer.parseInt(jwt.getSubject());
        } catch (NumberFormatException exception) {
            throw tokenInvalido();
        }
        if (id <= 0) {
            throw tokenInvalido();
        }

        UsuarioResponseDTO usuario;
        try {
            usuario = usuarioService.getUsuarioById(id);
        } catch (NoSuchElementException exception) {
            throw tokenInvalido();
        }
        if (usuario == null || !usuario.getStatus()) {
            throw tokenInvalido();
        }

        return new JwtAuthenticationToken(jwt, List.of(), Integer.toString(id));
    }

    private static OAuth2AuthenticationException tokenInvalido() {
        return new OAuth2AuthenticationException(new OAuth2Error("invalid_token"), "Sessão inválida.");
    }

}
