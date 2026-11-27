package com.example.api.dto;

public class LoginResponseDTO {

    public String token;
    
    public String nome;

    private Integer tempoValidade;

    public String perfilNome;

    private Long perfilId;

    public Long getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(Long perfilId) {
        this.perfilId = perfilId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getTempoValidade() {
        return tempoValidade;
    }

    public void setTempoValidade(Integer tempoValidade) {
        this.tempoValidade = tempoValidade;
    }

    public String getPerfilNome() {
        return perfilNome;
    }

    public void setPerfilNome(String perfilNome) {
        this.perfilNome = perfilNome;
    }

    
}
