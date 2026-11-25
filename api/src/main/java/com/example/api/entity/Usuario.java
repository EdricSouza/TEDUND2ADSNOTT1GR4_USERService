package com.example.api.entity;

import java.time.Instant;
import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "senha_hash", nullable = false)
    private String senha;

    @Column(name = "data_cadastro", nullable = false)
    private Instant dataCadastro;

    @Column(name = "ultimo_acesso", nullable = true)
    private Instant dataUltimoAcesso;

    @Column(name = "id_clinica", nullable = false)
    private int idClinica;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "motivo_inativacao", nullable = true)
    private String motivoInatividade;

    @Column(name = "data_inativacao", nullable = true)
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

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
    
}
