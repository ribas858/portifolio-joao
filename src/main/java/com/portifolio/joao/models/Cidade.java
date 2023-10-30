package com.portifolio.joao.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cod_cidade", unique = true)
    private UUID cod_cidade;

    @Column(name = "nome_cidade", length = 50, nullable = false)
    @NotBlank
    @Size(min = 2, max = 50)
    private String nome_cidade;

    @OneToOne(mappedBy = "endereco_to_cidade")
    private Endereco endereco;

}
