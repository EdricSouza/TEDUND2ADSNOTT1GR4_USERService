package com.example.api.model;

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

    @Column(name = "password", nullable = false)
    private String Senha;

    @Column(name = "data_cadastro", nullable = false)
    private Instant DataCadastro;

    @Column(name = "ultimo_acesso", nullable = false)
    private Instant DataUltimoAcesso;

    @Column(name = "id_clinica", nullable = false)
    private int IdClinica;

    @Column(name = "status", nullable = false)
    private String Status;

    @Column(name = "motivo_inativacao", nullable = false)
    private String MotivoInatividade;

    @Column(name = "data_inativacao", nullable = false)
    private Instant DataInatividade;
}
