package com.portifolio.joao.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portifolio.joao.models.Pais;
import com.portifolio.joao.repositories.PaisRepository;

@Service
public class PaisService {
    @Autowired
    private PaisRepository paisRepository;

    // CREATE
    @Transactional
    public Pais create(Pais objeto) {
        objeto.setCod_pais(null);
        return this.paisRepository.save(objeto);
    }

    // READ
    public Pais findById(Long id) {
        Optional<Pais> pais = this.paisRepository.findById(id);
        return pais.orElseThrow(() -> new RuntimeException(
            "Pais não encontrado!! id:" + id + ", tipo: " + Pais.class.getName()
        ));
    }

    // UPDATE
    @Transactional
    public Pais update(Pais objeto) {
        Pais pais = findById(objeto.getCod_pais());
        pais = objeto;
        return this.paisRepository.save(pais);
    }

    // DELETE
    public void delete(Long id) {
        findById(id);
        try {
            this.paisRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("[PAIS] Não é possivel excluir! Entidades relacionadas!!");
        }
    }

    // FIND ALL
    public List<Pais> find_all() {
        List<Pais> paises = this.paisRepository.findAll();
        return paises;
    }
}
