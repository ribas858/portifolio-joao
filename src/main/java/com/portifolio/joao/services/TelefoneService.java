package com.portifolio.joao.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portifolio.joao.models.Telefone;
import com.portifolio.joao.repositories.TelefoneRepository;

@Service
public class TelefoneService {

    @Autowired
    private TelefoneRepository telefoneRepository;

    // CREATE
    public List<Telefone> create(List<Telefone> objetosTelefones) {
        for (int i = 0; i < objetosTelefones.size(); i++) {
            objetosTelefones.get(i).setCod_telefone(null);
        }
        return this.telefoneRepository.saveAll(objetosTelefones);
    }

    // READ
    public Telefone findById(Long id) {
        Optional<Telefone> telefone = this.telefoneRepository.findById(id);
        return telefone.orElseThrow( () -> new RuntimeException (
            "Telefone não encontrado!! id:" + id + ", tipo: " + Telefone.class.getName()
        ));
    }

    // UPDATE
    public List<Telefone> update(List<Telefone> objetosTelefones) {
        List<Telefone> telefones = new ArrayList<Telefone>();
        for (Telefone obj : objetosTelefones ) {
            findById(obj.getCod_telefone());
            telefones.add(obj);
        }
        return this.telefoneRepository.saveAll(telefones);
    }

    // DELETE
    public void delete(Long id) {
        findById(id);
        try {
            this.telefoneRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("[TELEFONE] Não é possivel excluir! Entidades relacionadas!!");
        }
    }

    // DELETE ALL
    public void deleteAllByIds(List<Long> ids) {
        for (Long i : ids) {
            findById(i);
        }
        try {
            this.telefoneRepository.deleteAllByIdInBatch(ids);
        } catch (Exception e) {
            throw new RuntimeException("[TELEFONE LISTA] Não é possivel excluir! Entidades relacionadas!!");
        }
    }
    
}
