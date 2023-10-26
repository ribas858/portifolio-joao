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
@Table(name = Categoria.TABLE_NAME)
@Getter
@Setter
@EqualsAndHashCode
public class Categoria {
    public static final String TABLE_NAME = "categoria";


    // cod_categoria
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cod_categoria", unique = true)
    private UUID cod_categoria;

    // nome
    @Column(name = "nome", length = 30, nullable = false)
    @NotBlank
    @Size(min = 2, max = 30)
    private String nome;

    // cod_admin
    private Long cod_admin;
}
