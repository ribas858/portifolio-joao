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

import com.portifolio.joao.models.Cliente;
import com.portifolio.joao.services.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cliente")
@Validated
public class ClienteController {
    
    @Autowired
    private ClienteService clienteService;

    // CREATE
    @PostMapping
    @Validated
    public ResponseEntity<Cliente> create(@Valid @RequestBody Cliente objeto) {
        this.clienteService.create(objeto);

        URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(objeto.getCod_cliente())
            .toUri();
        
        return ResponseEntity.created(uri).build();
    }

    // READ
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        Cliente cliente = this.clienteService.findById(id);
        return ResponseEntity.ok().body(cliente);
    }
    

    // UPDATE
    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> update(@Valid @RequestBody Cliente objeto, @PathVariable Long id) {
        objeto.setCod_cliente(id);
        this.clienteService.update(objeto);
        return ResponseEntity.noContent().build();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // FIND ALL : COD ADMIN
    @GetMapping("/admin/{id}")
    public ResponseEntity<List<Cliente>> findAllClientesByCod_admin(@PathVariable Long id) {
        List<Cliente> clientes = this.clienteService.findAllClientesByCod_admin(id);
        return ResponseEntity.ok().body(clientes);
    }


}
