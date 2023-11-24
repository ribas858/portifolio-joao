package com.portifolio.joao.controllers;

import java.net.URI;

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

import com.portifolio.joao.models.Admin;
//import com.portifolio.joao.models.Telefone;
import com.portifolio.joao.services.AdminService;
// import com.portifolio.joao.services.EnderecoService;
// import com.portifolio.joao.services.TelefoneService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
@Validated
public class AdminController {
    

    @Autowired
    private AdminService adminService;

    // @Autowired
    // private EnderecoService enderecoService;

    // @Autowired
    // private TelefoneService telefoneService;

    
    // CREATE
    @PostMapping
    @Validated
    public ResponseEntity<Void> create(@Valid @RequestBody Admin objeto) {
        this.adminService.create(objeto);

        URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(objeto.getCod_admin())
            .toUri();

        return ResponseEntity.created(uri).build();
    }

    // READ
    @GetMapping("/{id}")
    public ResponseEntity<Admin> findById(@PathVariable Long id) {
        Admin admin = this.adminService.findById(id);
        return ResponseEntity.ok().body(admin);
    }

    // UPDATE
    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> update(@Valid @RequestBody Admin objeto, @PathVariable Long id) {
        objeto.setCod_admin(id);
        this.adminService.update(objeto);
        return ResponseEntity.noContent().build();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.adminService.delete(id);
        return ResponseEntity.noContent().build();

    }

}
