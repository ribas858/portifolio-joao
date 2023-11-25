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

import com.portifolio.joao.models.Cidade;
import com.portifolio.joao.services.CidadeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cidade")
@Validated
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    // CREATE
    @PostMapping
    @Validated
    public ResponseEntity<Cidade> create(@Valid @RequestBody Cidade objeto) {
        this.cidadeService.create(objeto);

        URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(objeto.getCod_cidade())
            .toUri();

        return ResponseEntity.created(uri).build();
        
    }

    // READ
    @GetMapping("/{id}")
    public ResponseEntity<Cidade> findById(@PathVariable Long id) {
        Cidade cidade = this.cidadeService.findById(id);

        return ResponseEntity.ok().body(cidade);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody Cidade objeto, @PathVariable Long id) {
        objeto.setCod_cidade(id);
        this.cidadeService.update(objeto);
        return ResponseEntity.noContent().build();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.cidadeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // FIND ALL
    @GetMapping("/estado/{uf}")
    public ResponseEntity<List<Cidade>> findAllCidadeByUF(@PathVariable String uf) {
        List<Cidade> cidades = this.cidadeService.findAllCidadesByEstadoUF(uf);
        return ResponseEntity.ok().body(cidades);
    }
    
}
