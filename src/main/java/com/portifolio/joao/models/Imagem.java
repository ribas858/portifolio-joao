package com.portifolio.joao.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    @ManyToOne
    @JoinColumn(name = "cod_admin", referencedColumnName = "cod_admin", nullable = false)
    private Admin imagem_to_admin;

    // cod_categoria
    @ManyToOne
    @JoinColumn(name = "cod_categoria", referencedColumnName = "cod_categoria", nullable = false)
    private Categoria imagem_to_categoria;

    // cod_cliente
    @ManyToOne
    @JoinColumn(name = "cod_cliente", referencedColumnName = "cod_cliente", nullable = false)
    private Cliente imagem_to_cliente;


}
