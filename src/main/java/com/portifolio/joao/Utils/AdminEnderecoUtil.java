package com.portifolio.joao.Utils;

import java.util.ArrayList;
import java.util.List;

import com.portifolio.joao.models.Endereco;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class AdminEnderecoUtil {
    
    private Long cod_admin;

    private List<Endereco> enderecos = new ArrayList<Endereco>();


}
