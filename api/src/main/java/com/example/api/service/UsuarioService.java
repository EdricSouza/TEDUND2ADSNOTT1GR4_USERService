package com.example.api.service;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.dto.UsuarioRequestDTO;
import com.example.api.dto.UsuarioResponseDTO;
import com.example.api.entity.Usuario;
import com.example.api.mapper.UsuarioMapper;
import com.example.api.repository.UsuarioRepository;

@Service 
public class UsuarioService {

    @Autowired 
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SenhaService senhaService;

    public UsuarioResponseDTO getUsuarioRepository(int id) {
        Usuario usuario = usuarioRepository.findById(id).get();
        return UsuarioMapper.toResponse(usuario);
    }

    public Usuario createUsuario(UsuarioRequestDTO usuario) {
        Usuario usuarioEntity = UsuarioMapper.toRequest(usuario);

        usuarioEntity.setDataCadastro(java.time.Instant.now());
        usuarioEntity.setStatus(true);
        usuarioEntity.setSenha(senhaService.gerarHash(usuario.getSenha()));

        return usuarioRepository.save(usuarioEntity);
    }

    public Usuario updateUsuario(int id, UsuarioRequestDTO usuario) {
        Usuario usuarioEntity = usuarioRepository.findById(id).get();

        usuarioEntity.setNome(usuario.getNome());
        usuarioEntity.setEmail(usuario.getEmail());
        usuarioEntity.setIdClinica(usuario.getIdClinica());
        usuarioEntity.setStatus(usuario.getStatus());

        return usuarioRepository.save(usuarioEntity);
    }

    public Usuario updateUsuarioStatus(int id, boolean status) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));

        usuario.setStatus(status);
        return usuarioRepository.save(usuario);
    }

    public UsuarioResponseDTO deleteUsuario(int id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));

        usuarioRepository.delete(usuario);

        return UsuarioMapper.toResponse(usuario);
    }

}
