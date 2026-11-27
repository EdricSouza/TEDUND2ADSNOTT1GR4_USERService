package com.example.api.dto;

import java.time.Instant;

public class UsuarioRequestDTO {

    public UsuarioRequestDTO() {
    }

    public UsuarioRequestDTO(Long id, String nome, String email, String senha, Instant dataCadastro, Instant dataUltimoAcesso,
            int idClinica, boolean status, String motivoInatividade, Instant dataInatividade) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.idClinica = idClinica;
        this.status = status;
        this.dataCadastro = dataCadastro;
    }

    private String nome;

    private String email;

    private String senha;

    private int idClinica;

    private Long perfilId;

    public Long getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(Long perfilId) {
        this.perfilId = perfilId;
    }

    private boolean status;

    private Instant dataCadastro;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdClinica() {
        return this.idClinica;
    }

    public void setIdClinica(int idClinica) {
        this.idClinica = idClinica;
    }

    public boolean getStatus() {
        return this.status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Instant getDataCadastro() {
        return this.dataCadastro;
    }

    public void setDataCadastro(Instant dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
