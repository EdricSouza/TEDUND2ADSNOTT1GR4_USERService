package com.example.api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.api.dto.UsuarioRequestDTO;
import com.example.api.entity.Perfil;
import com.example.api.entity.Usuario;
import com.example.api.mapper.UsuarioMapper;
import com.example.api.repository.UsuarioRepository;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.server.ResponseStatusException;

class UsuarioPerfilTest {
    private UsuarioService service;
    private UsuarioRepository repository;
    private PerfilService perfis;

    @BeforeEach
    void configurar() {
        service = new UsuarioService();
        repository = mock(UsuarioRepository.class);
        perfis = mock(PerfilService.class);
        var senhas = mock(SenhaService.class);
        ReflectionTestUtils.setField(service, "usuarioRepository", repository);
        ReflectionTestUtils.setField(service, "perfilService", perfis);
        ReflectionTestUtils.setField(service, "senhaService", senhas);
        when(repository.save(any(Usuario.class))).thenAnswer(invocation -> invocation.getArgument(0));
    }

    @Test
    void criaEAtualizaUsuarioComPerfil() {
        var request = new UsuarioRequestDTO();
        request.setPerfilId(3L);
        when(perfis.getPerfilById(3L)).thenReturn(new Perfil());
        var criado = service.createUsuario(request);
        assertEquals  (3L, criado.getPerfilId());
        assertEquals(3L, UsuarioMapper.toResponse(criado).getPerfilId());

        var usuario = new Usuario();
        when(repository.findById(1)).thenReturn(Optional.of(usuario));
        assertEquals(3L, service.updateUsuario(1, request).getPerfilId());
    }

    @Test
    void rejeitaPerfilAusenteOuInexistente() {
        var request = new UsuarioRequestDTO();
        when(perfis.getPerfilById(null)).thenThrow(new NoSuchElementException());
        assertThrows(ResponseStatusException.class, () -> service.createUsuario(request));
        request.setPerfilId(99L);
        when(perfis.getPerfilById(99L)).thenThrow(new NoSuchElementException());
        assertThrows(ResponseStatusException.class, () -> service.updateUsuario(1, request));
        verify(repository, never()).save(any());
    }
}
