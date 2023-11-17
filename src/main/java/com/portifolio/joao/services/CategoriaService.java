package com.portifolio.joao.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portifolio.joao.models.Admin;
import com.portifolio.joao.models.Categoria;
import com.portifolio.joao.repositories.CategoriaRepository;

import jakarta.transaction.Transactional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private AdminService adminService;

    // CREATE
    @Transactional
    public Categoria create(Categoria objeto) {
        Admin admin = this.adminService.findById(objeto.getCategoria_to_admin().getCod_admin());
        objeto.setCod_categoria(null);
        objeto.setCategoria_to_admin(admin);
        objeto = this.categoriaRepository.save(objeto);
        return objeto;
    }

    // READ
    public Categoria findById(UUID id) {
        Optional<Categoria> categoria = this.categoriaRepository.findById(id);

        return categoria.orElseThrow(() -> new RuntimeException(
            "Categoria não encontrada!! id:" + id + ", tipo: " + Categoria.class.getName()
        ));
    }

    // UPDATE
    @Transactional
    public Categoria update(Categoria objeto) {
        Categoria categoria = findById(objeto.getCod_categoria());
        categoria = objeto;
        return categoriaRepository.save(categoria);
    }

    // DELETE
    public void delete(UUID id) {
        findById(id);
        try {
            this.categoriaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("[CATEGORIA] Não é possivel excluir! Entidades relacionadas!!");
        }
    }
    
}
