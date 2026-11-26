package com.example.api.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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

    public UsuarioResponseDTO getUsuarioById(int id) {
        Usuario usuario = usuarioRepository.findById(id).get();
        return UsuarioMapper.toResponse(usuario);
    }

    public UsuarioResponseDTO getUsuarioByEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));
        return UsuarioMapper.toResponse(usuario);
    }

    public List<UsuarioResponseDTO> getUsuarioByIdClinica(int idClinica) {
        List<Usuario> usuarios = usuarioRepository.findByIdClinica(idClinica);
        return usuarios.stream()
                .map(UsuarioMapper::toResponse)
                .collect(Collectors.toList());
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

    public Usuario atualizarUltimoAcesso(int id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));

        usuario.setDataUltimoAcesso(java.time.Instant.now());
        return usuarioRepository.save(usuario);
    }

}
