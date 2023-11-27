package com.portifolio.joao.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portifolio.joao.models.Estado;
import com.portifolio.joao.repositories.EstadoRepository;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    // CREATE
    @Transactional
    public Estado create(Estado objeto) {
        objeto.setCod_estado(null);
        return this.estadoRepository.save(objeto);
    }

    // READ
    public Estado findById(Long id) {
        Optional<Estado> estado = this.estadoRepository.findById(id);
        return estado.orElseThrow(() -> new RuntimeException(
            "Estado não encontrado!! id:" + id + ", tipo: " + Estado.class.getName()
        ));
    }

    // UPDATE
    @Transactional
    public Estado update(Estado objeto) {
        Estado estado = findById(objeto.getCod_estado());
        estado = objeto;
        return this.estadoRepository.save(estado);
    }

    // DELETE
    public void delete(Long id) {
        findById(id);
        try {
            this.estadoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("[ESTADO] Não é possivel excluir! Entidades relacionadas!!");
        }
    }

    // FIND ALL
    public List<Estado> find_all_estados() {
        return this.estadoRepository.findAll();
    }
    
}
