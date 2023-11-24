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
@Table(name = Pais.TABLE_NAME)
@Getter
@Setter
@EqualsAndHashCode
public class Pais {
    public static final String TABLE_NAME = "pais";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_pais", unique = true)
    private Long cod_pais;

    @Column(name = "nome_pais", length = 50, nullable = false)
    @NotBlank
    @Size(min = 2, max = 50)
    private String nome_pais;

    @OneToMany(mappedBy = "endereco_to_pais")
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Endereco> enderecos = new ArrayList<Endereco>();
}
