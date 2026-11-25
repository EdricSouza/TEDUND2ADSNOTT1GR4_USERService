package com.example.api.dto;

import java.time.Instant;

public class UsuarioResponseDTO {

    public UsuarioResponseDTO() {
    }

    public UsuarioResponseDTO(Long id, String nome, String email, String senha, Instant dataCadastro, Instant dataUltimoAcesso,
            int idClinica, boolean status, String motivoInatividade, Instant dataInatividade) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.dataCadastro = dataCadastro;
        this.dataUltimoAcesso = dataUltimoAcesso;
        this.idClinica = idClinica;
        this.status = status;
        this.motivoInatividade = motivoInatividade;
        this.dataInatividade = dataInatividade;
    }

    private Long id;

    private String nome;

    private String email;

    private String senha;

    private Instant dataCadastro;

    private Instant dataUltimoAcesso;

    private int idClinica;

    private boolean status;

    private String motivoInatividade;

    private Instant dataInatividade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Instant dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Instant getDataUltimoAcesso() {
        return dataUltimoAcesso;
    }

    public void setDataUltimoAcesso(Instant dataUltimoAcesso) {
        this.dataUltimoAcesso = dataUltimoAcesso;
    }

    public int getIdClinica() {
        return idClinica;
    }

    public void setIdClinica(int idClinica) {
        this.idClinica = idClinica;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMotivoInatividade() {
        return motivoInatividade;
    }

    public void setMotivoInatividade(String motivoInatividade) {
        this.motivoInatividade = motivoInatividade;
    }

    public Instant getDataInatividade() {
        return dataInatividade;
    }

    public void setDataInatividade(Instant dataInatividade) {
        this.dataInatividade = dataInatividade;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
