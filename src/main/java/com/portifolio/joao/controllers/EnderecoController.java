package com.portifolio.joao.controllers;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

import com.portifolio.joao.Utils.AdminEnderecoUtil;
import com.portifolio.joao.models.Admin;
import com.portifolio.joao.models.Endereco;
import com.portifolio.joao.services.AdminService;
import com.portifolio.joao.services.EnderecoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/endereco")
@Validated
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private AdminService adminService;

    // CREATE
    @PostMapping
    @Validated
    public ResponseEntity<List<Endereco>> create(@Valid @RequestBody AdminEnderecoUtil objetos) {
        
        Admin admin = this.adminService.findById(objetos.getCod_admin());
        if (admin.getEnderecos().size() > 1) {
            throw new RuntimeException("[ENDERECO] Telefones cheio!! max -> 2");
        } else {
            objetos.setEnderecos(objetos.getEnderecos().subList(0, 2 - admin.getEnderecos().size()));
            
            this.enderecoService.create(objetos.getEnderecos());
            Integer size = admin.getEnderecos().size();
            
            for (Endereco endereco : objetos.getEnderecos()) {
                admin.getEnderecos().add(endereco);
                size++;
                if (size == 2) {
                    break;
                }
            }
            this.adminService.update(admin);
        }

        List<URI> uris = objetos.getEnderecos().stream()
            .map(objeto -> ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(objeto.getCod_endereco())
                    .toUri())
            .collect(Collectors.toList());
        
        return ResponseEntity.created(uris.get(0)).build();
    }

    // READ
    @GetMapping("{id}")
    public ResponseEntity<Endereco> findById(@PathVariable Long id) {
        Endereco endereco = this.enderecoService.findById(id);
        return ResponseEntity.ok().body(endereco);
    }


    // UPDATE
    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> update(@Valid @RequestBody List<Endereco> objetos, @PathVariable Long id) {
        this.enderecoService.update(objetos);

        return ResponseEntity.noContent().build();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.enderecoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // FIND ALL : COD ADMIN
    @GetMapping("/admin/{cod_admin}")
    public ResponseEntity<List<Endereco>> findAllEnderecoByCod_admin(@PathVariable Long cod_admin) {
        List<Endereco> enderecos = this.enderecoService.find_enderecos_admin(cod_admin);
        return ResponseEntity.ok().body(enderecos);
    }
    
}
