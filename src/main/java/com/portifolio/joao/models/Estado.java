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
@Table(name = Estado.TABLE_NAME)
@Getter
@Setter
@EqualsAndHashCode
public class Estado {
    public static final String TABLE_NAME = "estado";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_estado", unique = true)
    private Long cod_estado;

    @Column(name = "nome_estado", length = 50, nullable = false)
    @NotBlank
    @Size(min = 2, max = 50)
    private String nome_estado;

    @Column(name = "uf", length = 10, nullable = false)
    @NotBlank
    @Size(min = 2, max = 10)
    private String uf;

    @OneToMany(mappedBy = "endereco_to_estado")
    @JsonProperty(access = Access.WRITE_ONLY)
    private List<Endereco> enderecos = new ArrayList<Endereco>();
}
