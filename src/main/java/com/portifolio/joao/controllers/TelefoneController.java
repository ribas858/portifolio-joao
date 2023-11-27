package com.portifolio.joao.controllers;

import java.net.URI;
import java.util.List;
import java.util.Objects;
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

import com.portifolio.joao.Utils.AdminClienteUtil;
import com.portifolio.joao.Utils.TelefoneUtil;
import com.portifolio.joao.models.Telefone;
import com.portifolio.joao.services.AdminService;
import com.portifolio.joao.services.ClienteService;
import com.portifolio.joao.services.TelefoneService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/telefone")
@Validated
public class TelefoneController {
    
    @Autowired
    private TelefoneService telefoneService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private ClienteService clienteService;

    // CREATE
    @PostMapping
    @Validated
    public ResponseEntity<Telefone> create(@Valid @RequestBody TelefoneUtil objetos) {

        AdminClienteUtil adminCliente = this.telefoneService.add_telefone(objetos);

        if (Objects.nonNull(adminCliente.getAdmin())) {
            this.adminService.update(adminCliente.getAdmin());

        } else if (Objects.nonNull(adminCliente.getCliente())) {
            this.clienteService.update(adminCliente.getCliente());
        }

        List<URI> uris = objetos.getTelefones().stream()
            .map(objeto -> ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(objeto.getCod_telefone())
                    .toUri())
            .collect(Collectors.toList());
        
        return ResponseEntity.created(uris.get(0)).build();

    }

    // READ
    @GetMapping("/{id}")
    public ResponseEntity<Telefone> findById(@PathVariable Long id) {
        Telefone telefone = this.telefoneService.findById(id);
        return ResponseEntity.ok().body(telefone);
    }


    // UPDATE
    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> update(@Valid @RequestBody List<Telefone> objetos, @PathVariable Long id) {
        this.telefoneService.update(objetos);
        return ResponseEntity.noContent().build();
    }


    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        this.telefoneService.delete(id);

        return ResponseEntity.noContent().build();
    }

    // FIND ALL BY ADMIM
    @GetMapping("/admin/{cod_admin}")
    public ResponseEntity<List<Telefone>> find_all_by_admin(@PathVariable Long cod_admin) {
        List<Telefone> telefones = telefoneService.find_telefones_admin(cod_admin);
        return ResponseEntity.ok().body(telefones);
    }

    // FIND ALL BY CLIENTE
    @GetMapping("/cliente/{cod_cliente}")
    public ResponseEntity<List<Telefone>> find_all_by_cliente(@PathVariable Long cod_cliente) {
        List<Telefone> telefones = telefoneService.find_telefones_cliente(cod_cliente);
        return ResponseEntity.ok().body(telefones);
    }

}
