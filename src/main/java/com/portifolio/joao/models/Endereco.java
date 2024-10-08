package com.portifolio.joao.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = Endereco.TABLE_NAME)
@Getter
@Setter
@EqualsAndHashCode
public class Endereco {
    public static final String TABLE_NAME = "endereco";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_endereco", unique = true)
    private Long cod_endereco;

    @Column(name = "nome_rua", length = 50, nullable = false)
    @NotBlank
    @Size(min = 2, max = 50)
    private String nome_rua;

    @Column(name = "numero", nullable = false)
    @NotBlank
    private String numero;

    @Column(name = "quadra", nullable = false)
    @NotBlank
    private String quadra;

    @Column(name = "bairro", nullable = false)
    @NotBlank
    private String bairro;

    @Column(name = "complemento", nullable = false)
    @NotBlank
    private String complemento;

    @Column(name = "cep", nullable = false)
    @NotBlank
    private String cep;

    @ManyToOne
    @JoinColumn(name = "cod_cidade", referencedColumnName = "cod_cidade", nullable = false)
    private Cidade endereco_to_cidade;

    @ManyToOne
    @JoinColumn(name = "cod_estado", referencedColumnName = "cod_estado", nullable = false)
    private Estado endereco_to_estado;

    @ManyToOne
    @JoinColumn(name = "cod_pais", referencedColumnName = "cod_pais", nullable = false)
    private Pais endereco_to_pais;

}
