package com.example.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "perfil")
public class Perfil {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id_perfil")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nomePerfil;
    
    @Column (name = "descricao", nullable = false)
    private String descricaoPerfil;
}
