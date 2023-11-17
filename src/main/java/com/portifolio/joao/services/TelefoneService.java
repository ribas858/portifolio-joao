package com.portifolio.joao.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.portifolio.joao.models.Telefone;
import com.portifolio.joao.repositories.TelefoneRepository;

@Service
public class TelefoneService {

    @Autowired
    private TelefoneRepository telefoneRepository;

    // CREATE
    public Telefone create(Telefone objeto) {
        objeto.setCod_telefone(null);
        return this.telefoneRepository.save(objeto);
    }

    // READ
    public Telefone findById(UUID id) {
        Optional<Telefone> telefone = this.telefoneRepository.findById(id);
        return telefone.orElseThrow( () -> new RuntimeException (
            "Telefone não encontrado!! id:" + id + ", tipo: " + Telefone.class.getName()
        ));
    }

    // UPDATE
    public Telefone update(Telefone objeto) {
        Telefone telefone = findById(objeto.getCod_telefone());
        telefone = objeto;
        return this.telefoneRepository.save(telefone);
    }

    // DELETE
    public void delete(UUID id) {
        findById(id);
        try {
            this.telefoneRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("[TELEFONE] Não é possivel excluir! Entidades relacionadas!!");
        }
    }
    
}
