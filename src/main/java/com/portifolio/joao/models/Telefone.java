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
@Table(name = Telefone.TABLE_NAME)
@Getter
@Setter
@EqualsAndHashCode
public class Telefone {
    public static final String TABLE_NAME = "telefone";

    // cod telefone
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "cod_telefone", unique = true)
    private UUID cod_telefone;

    // fone
    @Column(name = "fone", nullable = false)
    @NotBlank
    private String fone;
}
