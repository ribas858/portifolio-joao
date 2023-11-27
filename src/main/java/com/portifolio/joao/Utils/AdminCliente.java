package com.portifolio.joao.Utils;

import com.portifolio.joao.models.Admin;
import com.portifolio.joao.models.Cliente;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@EqualsAndHashCode
public class AdminCliente {
    
    private Admin admin;

    private Cliente cliente;
}
