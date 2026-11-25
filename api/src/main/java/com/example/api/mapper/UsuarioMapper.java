package com.example.api.mapper;

import com.example.api.dto.UsuarioRequestDTO;
import com.example.api.dto.UsuarioResponseDTO;
import com.example.api.entity.Usuario;

public class UsuarioMapper {
    
    private UsuarioMapper() {

    }

    public static UsuarioResponseDTO toResponse(Usuario usuario) {
        return new UsuarioResponseDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getSenha(),
            usuario.getDataCadastro(),
            usuario.getDataUltimoAcesso(),
            usuario.getIdClinica(),
            usuario.getStatus(),
            usuario.getMotivoInatividade(),
            usuario.getDataInatividade()
        );
    }

    public static Usuario toRequest(UsuarioRequestDTO request) {
        Usuario usuario = new Usuario();

        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setIdClinica(request.getIdClinica());
        usuario.setStatus(true);

        return usuario;
    }
}
