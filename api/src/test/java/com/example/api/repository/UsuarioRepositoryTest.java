package com.example.api.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.api.entity.Usuario;

@SpringBootTest(properties = {
    "spring.datasource.url=jdbc:h2:mem:usuarios_test;DB_CLOSE_DELAY=-1",
    "app.auth.jwt-secret=chave-apenas-para-testes-com-32-bytes"
})
@Transactional
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void buscaSomenteUsuariosDaClinicaInformada() {
        Usuario primeiro = criarUsuario("Primeiro", 1);
        Usuario segundo = criarUsuario("Segundo", 2);
        usuarioRepository.saveAndFlush(primeiro);
        usuarioRepository.saveAndFlush(segundo);

        List<Usuario> usuarios = usuarioRepository.findByIdClinica(1);

        assertEquals(1, usuarios.size());
        assertEquals("Primeiro", usuarios.get(0).getNome());
    }

    private Usuario criarUsuario(String nome, int idClinica) {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(nome + "@exemplo.com");
        usuario.setSenha("hash");
        usuario.setDataCadastro(Instant.now());
        usuario.setIdClinica(idClinica);
        usuario.setStatus(true);
        return usuario;
    }
}
