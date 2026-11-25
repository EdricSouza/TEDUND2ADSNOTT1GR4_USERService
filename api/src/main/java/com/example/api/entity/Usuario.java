package com.example.api.entity;

import java.time.Instant;
import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long Id;

    @Column(name = "nome", nullable = false)
    private String Nome;

    @Column(name = "email", nullable = false)
    private String Email;

    @Column(name = "senha_hash", nullable = false)
    private String Senha;

    @Column(name = "data_cadastro", nullable = false)
    private Instant DataCadastro;

    @Column(name = "ultimo_acesso", nullable = true)
    private Instant DataUltimoAcesso;

    @Column(name = "id_clinica", nullable = false)
    private int IdClinica;

    @Column(name = "status", nullable = false)
    private boolean Status;

    @Column(name = "motivo_inativacao", nullable = true)
    private String MotivoInatividade;

    @Column(name = "data_inativacao", nullable = true)
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

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String senha) {
        Senha = senha;
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
