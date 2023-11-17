package com.portifolio.joao.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portifolio.joao.models.Admin;
import com.portifolio.joao.repositories.AdminRepository;

@Service
public class AdminService {
    
    @Autowired
    private AdminRepository adminRepository;
    
    // CREATE
    @Transactional
    public Admin create(Admin objeto) {
        objeto.setCod_admin(null);
        objeto = this.adminRepository.save(objeto);
        return objeto;
    }

    // READ
    public Admin findById(UUID id) {
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
        admin = objeto;
        return this.adminRepository.save(admin);
    }

    // DELETE
    public void delete(UUID id) {
        findById(id);
        try {
            this.adminRepository.deleteById(id);

        } catch (Exception e) {
            throw new RuntimeException("[ADMIN] Não é possivel excluir! Entidades relacionadas!!");
        }
    }
}
