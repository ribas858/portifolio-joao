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
@Table(name = Admin.TABLE_NAME)
@Getter
@Setter
@EqualsAndHashCode
public class Admin {

    public static final String TABLE_NAME = "admin";
    
    // cod_admin
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cod_admin", unique = true)
    private UUID cod_admin;

    // nome
    @NotBlank
    @Column(name = "nome", length = 20, nullable = false)
    @Size(min = 2, max = 20)
    private String nome;

    // sobrenome
    @NotBlank
    @Column(name = "sobrenome", length = 50, nullable = false)
    @Size(min = 2, max = 50)
    private String sobrenome;

    // email
    @NotBlank
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    // senha
    @NotBlank
    @Column(name = "senha", length = 100, nullable = false)
    @Size(min = 8, max = 100)
    private String senha;

    // salt

}
