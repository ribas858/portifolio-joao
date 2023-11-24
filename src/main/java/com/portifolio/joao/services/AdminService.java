package com.portifolio.joao.services;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portifolio.joao.models.Admin;
import com.portifolio.joao.models.Endereco;
import com.portifolio.joao.models.Telefone;
import com.portifolio.joao.repositories.AdminRepository;

@Service
public class AdminService {
    
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private TelefoneService telefoneService;
    
    // CREATE
    @Transactional
    public Admin create(Admin objeto) {
        objeto.setCod_admin(null);
        this.enderecoService.create(objeto.getEnderecos());
        this.telefoneService.create(objeto.getTelefones());
        objeto = this.adminRepository.save(objeto);
        return objeto;
    }

    // READ
    public Admin findById(Long id) {
        Optional<Admin> admin = this.adminRepository.findById(id);
        return admin.orElseThrow(() -> new RuntimeException (
            "Admin não encontrado!! id:" + id + ", tipo: " + Admin.class.getName()
            )
        );
    }

    // UPDATE
    @Transactional
    public Admin update(Admin objeto) {
        Admin admin = findById(objeto.getCod_admin());
        this.enderecoService.update(objeto.getEnderecos());
        this.telefoneService.update(objeto.getTelefones());
        admin = objeto;
        return this.adminRepository.save(admin);
    }

    // DELETE
    public void delete(Long id) {
        Admin admin = findById(id);
        List<Long> enderecoIds = new ArrayList<Long>();
        List<Long> telefonesIds = new ArrayList<Long>();
        for (Endereco end : admin.getEnderecos()) {
            enderecoIds.add(end.getCod_endereco());
        }
        for (Telefone tel : admin.getTelefones()) {
            telefonesIds.add(tel.getCod_telefone());
        }

        try {
            this.adminRepository.deleteById(id);
            this.enderecoService.deleteAllByIds(enderecoIds);
            this.telefoneService.deleteAllByIds(telefonesIds);
        } catch (Exception e) {
            throw new RuntimeException("[ADMIN] Não é possivel excluir! Entidades relacionadas!!");
        }
    }
}
