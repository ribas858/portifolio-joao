package com.portifolio.joao.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portifolio.joao.models.Admin;
import com.portifolio.joao.models.Cliente;
import com.portifolio.joao.models.Imagem;
import com.portifolio.joao.repositories.ClienteRepository;
import com.portifolio.joao.repositories.ImagemRepository;

@Service
public class ClienteService {
    
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AdminService adminService;

    @Autowired
    private TelefoneService telefoneService;

    @Autowired
    private ImagemRepository imagemRepository;

    // CREATE
    @Transactional
    public Cliente create(Cliente objeto) {
        Admin admin = this.adminService.findById(objeto.getCliente_to_admin().getCod_admin());
        objeto.setCod_cliente(null);
        objeto.setCliente_to_admin(admin);

        this.telefoneService.create(objeto.getTelefones());

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
    @Transactional
    public Cliente update(Cliente objeto) {
        Cliente cliente = findById(objeto.getCod_cliente());
        cliente.setCaminho_foto(objeto.getCaminho_foto());
        
        cliente.setCod_cliente(objeto.getCod_cliente());
        cliente.setEmail(objeto.getEmail());
        cliente.setImagens(objeto.getImagens());
        cliente.setNome(objeto.getNome());
        cliente.setSobrenome(objeto.getSobrenome());
        cliente.setTelefones(objeto.getTelefones());

        this.telefoneService.update(cliente.getTelefones());
        //cliente = objeto;
        return this.clienteRepository.save(cliente);
    }

    // DELETE
    public void delete(Long id) {
        Cliente cliente = findById(id);
        List<Long> imagemIds = new ArrayList<Long>();
        for (Imagem img : cliente.getImagens()) {
            imagemIds.add(img.getCod_imagem());
        }
        try {
            for (Long i : imagemIds) {
                this.imagemRepository.findById(i);
            }
            this.imagemRepository.deleteAllByIdInBatch(imagemIds);

            Admin admin = this.adminService.findById(cliente.getCliente_to_admin().getCod_admin());
            admin.getClientes().remove(cliente);
            
            this.clienteRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("[CLIENTE] Não é possivel excluir! Entidades relacionadas!!");
        }
    }

    // FIND ALL : COD ADMIN
    public List<Cliente> findAllClientesByCod_admin(Long id) {
        List<Cliente> clientes = this.clienteRepository.findAllClientesByCod_admin(id);
        return clientes;
    }

    

}
