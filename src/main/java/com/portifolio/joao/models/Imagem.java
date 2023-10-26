package com.portifolio.joao.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = Imagem.TABLE_NAME)
@Getter
@Setter
@EqualsAndHashCode
public class Imagem {
    public static final String TABLE_NAME = "imagem";


    // cod_imagem
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cod_imagem", unique = true)
    private UUID cod_imagem;

    // titulo
    @Column(name = "titulo", nullable = false)
    @NotBlank
    private String titulo;

    // data
    @Column(name = "caminho", nullable = false)
    @NotBlank
    private String caminho;

    // descricao
    @Column(name = "descricao", length = 200, nullable = true)
    private String descricao;

    // cod_admin
    private Long cod_admin;

    // cod_categoria
    private Long cod_categoria;

    // cod_cliente
    private Long cod_cliente;


}
