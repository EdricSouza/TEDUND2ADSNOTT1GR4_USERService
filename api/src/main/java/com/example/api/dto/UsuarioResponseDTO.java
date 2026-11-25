package com.example.api.dto;

import java.time.Instant;

public class UsuarioResponseDTO {

    public UsuarioResponseDTO() {
    }

    public UsuarioResponseDTO(Long id, String nome, String email, Instant dataCadastro, Instant dataUltimoAcesso,
            int idClinica, boolean status, String motivoInatividade, Instant dataInatividade) {
        Id = id;
        Nome = nome;
        Email = email;
        DataCadastro = dataCadastro;
        DataUltimoAcesso = dataUltimoAcesso;
        IdClinica = idClinica;
        Status = status;
        MotivoInatividade = motivoInatividade;
        DataInatividade = dataInatividade;
    }

    private Long Id;

    private String Nome;

    private String Email;

    private Instant DataCadastro;

    private Instant DataUltimoAcesso;

    private int IdClinica;

    private boolean Status;

    private String MotivoInatividade;

    private Instant DataInatividade;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Instant getDataCadastro() {
        return DataCadastro;
    }

    public void setDataCadastro(Instant dataCadastro) {
        DataCadastro = dataCadastro;
    }

    public Instant getDataUltimoAcesso() {
        return DataUltimoAcesso;
    }

    public void setDataUltimoAcesso(Instant dataUltimoAcesso) {
        DataUltimoAcesso = dataUltimoAcesso;
    }

    public int getIdClinica() {
        return IdClinica;
    }

    public void setIdClinica(int idClinica) {
        IdClinica = idClinica;
    }

    public boolean getStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    public String getMotivoInatividade() {
        return MotivoInatividade;
    }

    public void setMotivoInatividade(String motivoInatividade) {
        MotivoInatividade = motivoInatividade;
    }

    public Instant getDataInatividade() {
        return DataInatividade;
    }

    public void setDataInatividade(Instant dataInatividade) {
        DataInatividade = dataInatividade;
    }

    

}
