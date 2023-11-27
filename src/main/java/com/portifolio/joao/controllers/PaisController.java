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

import com.portifolio.joao.models.Pais;
import com.portifolio.joao.services.PaisService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pais")
@Validated
public class PaisController {
    
    @Autowired
    private PaisService paisService;

    // CREATE
    @PostMapping
    @Validated
    public ResponseEntity<Pais> create(@Valid @RequestBody Pais objeto) {
        this.paisService.create(objeto);

        URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(objeto.getCod_pais())
            .toUri();
        
        return ResponseEntity.created(uri).build();
    }

    // READ
    @GetMapping("/{id}")
    public ResponseEntity<Pais> findById(@PathVariable Long id) {
        Pais pais = this.paisService.findById(id);
        return ResponseEntity.ok().body(pais);
    }

    // UPDATE
    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> update(@Valid @RequestBody Pais objeto, @PathVariable Long id) {
        objeto.setCod_pais(id);
        this.paisService.update(objeto);
        return ResponseEntity.noContent().build();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.paisService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // FIND ALL
    @GetMapping("/all")
    public ResponseEntity<List<Pais>> find_all(){
        List<Pais> paises = this.paisService.find_all();
        return ResponseEntity.ok().body(paises);
    }
}
