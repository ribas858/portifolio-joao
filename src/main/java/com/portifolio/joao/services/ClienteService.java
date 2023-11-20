package com.portifolio.joao.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portifolio.joao.models.Admin;
import com.portifolio.joao.models.Cliente;
import com.portifolio.joao.repositories.ClienteRepository;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AdminService adminService;

    // CREATE
    public Cliente create(Cliente objeto) {
        Admin admin = this.adminService.findById(objeto.getCliente_to_admin().getCod_admin());
        objeto.setCod_cliente(null);
        objeto.setCliente_to_admin(admin);
        return this.clienteRepository.save(objeto);
    }

    // READ
    public Cliente findById(Long id) {
        Optional<Cliente> cliente = this.clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new RuntimeException(
            "Cliente não encontrado!! id:" + id + ", tipo: " + Cliente.class.getName()
        ));
    }

    // UPDATE
    public Cliente update(Cliente objeto) {
        Cliente cliente = findById(objeto.getCod_cliente());
        cliente = objeto;
        return this.clienteRepository.save(cliente);
    }

    // DELETE
    public void delete(Long id) {
        findById(id);
        try {
            this.clienteRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("[CLIENTE] Não é possivel excluir! Entidades relacionadas!!");
        }
    }

}
