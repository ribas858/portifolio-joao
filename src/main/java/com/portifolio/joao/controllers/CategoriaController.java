package com.portifolio.joao.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.portifolio.joao.models.Categoria;
import com.portifolio.joao.services.CategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categoria")
@Validated
public class CategoriaController {
    
    @Autowired
    private CategoriaService categoriaService;
    
    // CREATE
    @PostMapping
    @Validated
    public ResponseEntity<Categoria> create(@Valid @RequestBody Categoria objeto) {
        this.categoriaService.create(objeto);
        URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(objeto.getCod_categoria())
            .toUri();
        return ResponseEntity.created(uri).build();
    }

    // READ
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> findById(@PathVariable Long id) {
        Categoria categoria = this.categoriaService.findById(id);
        return ResponseEntity.ok().body(categoria);
    }

    // UPDATE
    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> update(@Valid @RequestBody Categoria categoria, @PathVariable Long id) {
        categoria.setCod_categoria(id);
        this.categoriaService.update(categoria);
        return ResponseEntity.noContent().build();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        //System.out.println("\n\nID>>>>>>>>>>>>>>>>" + id + "\n\n");
        this.categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/admin/{cod_admin}")
    public ResponseEntity<List<Categoria>> findAllCategoriasByCod_admin(@PathVariable Long cod_admin) {
        List<Categoria> categorias = this.categoriaService.findAllCategoriaByCod_admin(cod_admin);
        
        return ResponseEntity.ok().body(categorias);
    }
}
