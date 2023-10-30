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
@Table(name = Estado.TABLE_NAME)
@Getter
@Setter
@EqualsAndHashCode
public class Estado {
    public static final String TABLE_NAME = "estado";

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cod_estado", unique = true)
    private UUID cod_estado;

    @Column(name = "nome_estado", length = 50, nullable = false)
    @NotBlank
    @Size(min = 2, max = 50)
    private String nome_estado;

    @OneToOne(mappedBy = "endereco_to_estado")
    private Endereco endereco;
}
