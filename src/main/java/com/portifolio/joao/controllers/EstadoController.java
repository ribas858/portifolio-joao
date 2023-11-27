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

import com.portifolio.joao.models.Estado;
import com.portifolio.joao.services.EstadoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/estado")
@Validated
public class EstadoController {
    

    @Autowired
    private EstadoService estadoService;

    // CREATE
    @PostMapping
    @Validated
    public ResponseEntity<Estado> create(@Valid @RequestBody Estado objeto) {
        this.estadoService.create(objeto);

        URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(objeto.getCod_estado())
            .toUri();
        
        return ResponseEntity.created(uri).build();
    }

    // READ
    @GetMapping("/{id}")
    public ResponseEntity<Estado> findById(@PathVariable Long id) {
        Estado estado = this.estadoService.findById(id);
        return ResponseEntity.ok().body(estado);
    }

    // UPDATE
    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> update(@Valid @RequestBody Estado objeto, @PathVariable Long id) {
        objeto.setCod_estado(id);
        this.estadoService.update(objeto);
        return ResponseEntity.noContent().build();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.estadoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // FIND ALL
    @GetMapping("/all")
    public ResponseEntity<List<Estado>> find_all() {
        List<Estado> estados = this.estadoService.find_all_estados();
        return ResponseEntity.ok().body(estados);
    }

}
