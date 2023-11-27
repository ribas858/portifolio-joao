package com.portifolio.joao.Utils;

import java.util.ArrayList;
import java.util.List;

import com.portifolio.joao.models.Telefone;

import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class TelefoneUtil {
    
    
    private Long cod_admin;

    private Long cod_cliente;

    @Size(min = 1, max = 2)
    private List<Telefone> telefones = new ArrayList<Telefone>();

}
