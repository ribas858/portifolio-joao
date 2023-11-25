package com.portifolio.joao.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portifolio.joao.models.Admin;
import com.portifolio.joao.models.Categoria;
import com.portifolio.joao.repositories.CategoriaRepository;


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
    public Categoria findById(Long id) {
        Optional<Categoria> categoria = this.categoriaRepository.findById(id);

        return categoria.orElseThrow(() -> new RuntimeException(
            "Categoria não encontrada!! id:" + id + ", tipo: " + Categoria.class.getName()
        ));
    }

    // UPDATE
    @Transactional
    public Categoria update(Categoria objeto) {
        Categoria categoria = findById(objeto.getCod_categoria());
        categoria.setImagems(objeto.getImagems());
        categoria.setNome(objeto.getNome());
        return categoriaRepository.save(categoria);
    }

    // DELETE
    public void delete(Long id) {
        findById(id);
        try {
            this.categoriaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("[CATEGORIA] Não é possivel excluir! Entidades relacionadas!!");
        }
    }

    public List<Categoria> findAllCategoriaByCod_admin(Long id) {
        this.adminService.findById(id);
        List<Categoria> categorias = this.categoriaRepository.findAllCategoriaByCod_admin(id);
        return categorias;
    }
    
}
