package com.example.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
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

        Long id = usuario.getId();
        usuarioService.atualizarUltimoAcesso(id.intValue());

        

        return "Login bem-sucedido";
    }

    // @Transactional(readOnly = true)
    // public JwtAuthenticationToken autenticarToken(Jwt jwt) {
    //     int id;

    //     try { 
    //         id = Integer.parseInt(jwt.getSubject()); 
    //     }

    //     catch (NumberFormatException exception) { 
    //         throw tokenInvalido(); 
    //     }

    //     UsuarioResponseDTO usuario = usuarioService.getUsuarioById(id);

    //     var perfis = usuario.getNomesPerfisAutorizados();

    //     if (!usuario.getStatus() || perfis.isEmpty()) throw tokenInvalido();

    //     var authorities = perfis.stream().map(p -> new SimpleGrantedAuthority("ROLE_" + p)).toList();

    //     return new JwtAuthenticationToken(jwt, authorities, Long.toString(id));
    // }

    private static OAuth2AuthenticationException tokenInvalido() {
        return new OAuth2AuthenticationException(new OAuth2Error("invalid_token"), "Sessão inválida.");
    }

}
