package com.portifolio.joao.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_categoria", unique = true)
    private Long cod_categoria;

    // nome
    @Column(name = "nome", length = 30, nullable = false, unique = true)
    @NotBlank
    @Size(min = 2, max = 30)
    private String nome;

    // cod_admin
    @ManyToOne
    @JoinColumn(name = "cod_admin",referencedColumnName = "cod_admin", nullable = false)
    private Admin categoria_to_admin;

    @OneToMany(mappedBy = "imagem_to_categoria")
    private List<Imagem> imagems = new ArrayList<Imagem>();
}
