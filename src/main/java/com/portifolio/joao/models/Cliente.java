package com.portifolio.joao.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = Cliente.TABLE_NAME)
@Getter
@Setter
@EqualsAndHashCode
public class Cliente {
    public static final String TABLE_NAME = "cliente";

    // cod_cliente
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cod_cliente", unique = true)
    private UUID cod_cliente;

    // nome
    @Column(name = "nome", length = 20, nullable = false)
    @NotBlank
    @Size(min = 2, max = 20)
    private String nome;

    // sobrenome
    @Column(name = "sobrenome", length = 50, nullable = true)
    @Size(min = 2, max = 50)
    private String sobrenome;

    // email
    @Column(name = "email", nullable = true)
    private String email;

    // foto
    @Column(name = "caminho_foto", nullable = true)
    private String caminho_foto;
    
    // cod_admin
    private Long cod_admin;


}
