package com.portifolio.joao.models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
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
    @JsonProperty(access = Access.WRITE_ONLY)
    @NotBlank
    @Column(name = "senha", length = 100, nullable = false)
    @Size(min = 8, max = 100)
    private String senha;

    // salt

    @OneToMany(mappedBy = "categoria_to_admin")
    private List<Categoria> categorias = new ArrayList<Categoria>();

    @OneToMany(mappedBy = "imagem_to_admin")
    private List<Imagem> imagens = new ArrayList<Imagem>();

    @OneToMany(mappedBy = "cliente_to_admin")
    private List<Cliente> clientes = new ArrayList<Cliente>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable( name = "admin_telefone",
                joinColumns = @JoinColumn(name = "cod_admin"),
                inverseJoinColumns = @JoinColumn(name = "cod_telefone"),
                uniqueConstraints = @UniqueConstraint(columnNames = {"cod_admin", "cod_telefone"})
    )
    @Size(min = 1, max = 2)
    private List<Telefone> telefones = new ArrayList<Telefone>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable( name = "admin_endereco",
                joinColumns = @JoinColumn(name = "cod_admin"),
                inverseJoinColumns = @JoinColumn(name = "cod_endereco"),
                uniqueConstraints = @UniqueConstraint(columnNames = {"cod_admin", "cod_endereco"})
    )
    @Size(min = 1, max = 2)
    private List<Endereco> enderecos = new ArrayList<Endereco>();

}
