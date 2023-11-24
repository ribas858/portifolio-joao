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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = Cidade.TABLE_NAME)
@Getter
@Setter
@EqualsAndHashCode
public class Cidade {
    public static final String TABLE_NAME = "cidade";


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_cidade", unique = true)
    private Long cod_cidade;

    @Column(name = "nome_cidade", length = 50, nullable = false)
    @NotBlank
    @Size(min = 2, max = 50)
    private String nome_cidade;

    @Column(name = "estado_cidade", length = 10, nullable = false)
    @NotBlank
    @Size(min = 2, max = 10)
    private String estado_cidade;

    @OneToMany(mappedBy = "endereco_to_cidade")
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Endereco> enderecos = new ArrayList<Endereco>();

}
