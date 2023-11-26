package com.portifolio.joao.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_cliente", unique = true)
    private Long cod_cliente;

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
    @ManyToOne
    @JoinColumn(name = "cod_admin", referencedColumnName = "cod_admin", nullable = false)
    private Admin cliente_to_admin;

    @OneToMany(mappedBy = "imagem_to_cliente")
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Imagem> imagens = new ArrayList<Imagem>();

    @ManyToMany
    @JoinTable( name = "cliente_telefone",
                joinColumns = @JoinColumn(name = "cod_cliente"),
                inverseJoinColumns = @JoinColumn(name = "cod_telefone"),
                uniqueConstraints = @UniqueConstraint(columnNames = {"cod_cliente", "cod_telefone"})
    )
    @Size(min = 1, max = 2)
    private List<Telefone> telefones = new ArrayList<Telefone>();


}
