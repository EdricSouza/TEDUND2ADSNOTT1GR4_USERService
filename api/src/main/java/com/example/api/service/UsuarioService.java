package com.example.api.service;

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

}
