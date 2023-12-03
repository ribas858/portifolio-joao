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

import com.portifolio.joao.models.Imagem;
import com.portifolio.joao.services.ImagemService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/imagem")
@Validated
public class ImagemController {

    @Autowired
    private ImagemService imagemService;

    // CREATE
    @PostMapping
    @Validated
    public ResponseEntity<Imagem> create(@Valid @RequestBody Imagem objeto) {
        this.imagemService.create(objeto);

        URI uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(objeto.getCod_imagem())
            .toUri();

        return ResponseEntity.created(uri).build();
    }

    // READ
    @GetMapping("/{id}")
    public ResponseEntity<Imagem> findById(@PathVariable Long id) {
        Imagem imagem = imagemService.findById(id);
        return ResponseEntity.ok().body(imagem);
    }

    // UPDATE
    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> update(@Valid @RequestBody Imagem objeto, @PathVariable Long id) {
        objeto.setCod_imagem(id);
        this.imagemService.update(objeto);

        return ResponseEntity.noContent().build();

    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.imagemService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // FIND ALL : COD CATEGORIA
    @GetMapping("/categoria/{cod_categoria}")
    public ResponseEntity<List<Imagem>> findAllImagensByCod_categoria(@PathVariable Long cod_categoria) {
        List<Imagem> imagens = this.imagemService.findAllImagemByCod_categoria(cod_categoria);
        return ResponseEntity.ok().body(imagens);
    }

    // FIND ALL : COD CLIENTE
    @GetMapping("/cliente/{cod_cliente}")
    public ResponseEntity<List<Imagem>> findAllImagensByCod_cliente(@PathVariable Long cod_cliente) {
        List<Imagem> imagens = this.imagemService.findAllImagemByCod_cliente(cod_cliente);
        return ResponseEntity.ok().body(imagens);
    }

    // FIND ALL Imagens
    @GetMapping
    public ResponseEntity<List<Imagem>> findAll() {
        List<Imagem> imagens = this.imagemService.find_all();
        return ResponseEntity.ok().body(imagens);
    }


    
}
