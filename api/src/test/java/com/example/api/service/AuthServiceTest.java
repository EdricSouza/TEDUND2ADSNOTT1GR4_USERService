package com.example.api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.api.dto.UsuarioResponseDTO;
import com.example.api.entity.Perfil;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.test.util.ReflectionTestUtils;

class AuthServiceTest {
    private AuthService authService;
    private UsuarioService usuarioService;

    @BeforeEach
    void configurar() {
        authService = new AuthService();
        usuarioService = mock(UsuarioService.class);
        ReflectionTestUtils.setField(authService, "usuarioService", usuarioService);
    }

    private Jwt token(String subject) {
        return Jwt.withTokenValue("token").header("alg", "HS256").subject(subject).build();
    }

    @Test
    void autenticaUsuarioAtivo() {
        var usuario = new UsuarioResponseDTO();
        usuario.setStatus(true);
        when(usuarioService.getUsuarioById(42)).thenReturn(usuario);
        var authentication = authService.autenticarToken(token("42"));
        assertEquals("42", authentication.getName());
        assertTrue(authentication.getAuthorities().isEmpty());
    }

    @Test
    void rejeitaUsuarioInativoOuExcluido() {
        var usuario = new UsuarioResponseDTO();
        when(usuarioService.getUsuarioById(42)).thenReturn(usuario);
        assertThrows(OAuth2AuthenticationException.class, () -> authService.autenticarToken(token("42")));
        when(usuarioService.getUsuarioById(42)).thenThrow(new NoSuchElementException());
        assertThrows(OAuth2AuthenticationException.class, () -> authService.autenticarToken(token("42")));
    }

    @Test
    void rejeitaIdentificadorInvalido() {
        for (String subject : new String[] {"abc", "0", "-1", "999999999999999"}) {
            assertThrows(OAuth2AuthenticationException.class, () -> authService.autenticarToken(token(subject)));
        }
        verifyNoInteractions(usuarioService);
    }

    @Test
    void loginRetornaPerfilEValidade() {
        var senhaService = mock(SenhaService.class);
        var jwtService = mock(JwtService.class);
        var perfilService = mock(PerfilService.class);
        ReflectionTestUtils.setField(authService, "senhaService", senhaService);
        ReflectionTestUtils.setField(authService, "jwtService", jwtService);
        ReflectionTestUtils.setField(authService, "perfilService", perfilService);
        ReflectionTestUtils.setField(authService, "tempoValidade", 60);

        var usuario = new UsuarioResponseDTO();
        usuario.setId(42L);
        usuario.setNome("Usuário");
        usuario.setSenha("hash");
        usuario.setStatus(true);
        usuario.setPerfilId(3L);
        var perfil = new Perfil();
        perfil.setNomePerfil("ADMIN");
        when(usuarioService.getUsuarioByEmail("teste@exemplo.com")).thenReturn(usuario);
        when(senhaService.verificar("senha", "hash")).thenReturn(true);
        when(perfilService.getPerfilById(3L)).thenReturn(perfil);
        when(jwtService.emitir(42L)).thenReturn("jwt");

        var resposta = authService.login("teste@exemplo.com", "senha");
        assertEquals("jwt", resposta.getToken());
        assertEquals("Usuário", resposta.getNome());
        assertEquals(3L, resposta.getPerfilId());
        assertEquals("ADMIN", resposta.getPerfilNome());
        assertEquals(60, resposta.getTempoValidade());
        verify(usuarioService).atualizarUltimoAcesso(42);
    }
}
