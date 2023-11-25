package com.portifolio.joao.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portifolio.joao.models.Cidade;
import com.portifolio.joao.repositories.CidadeRepository;

@Service
public class CidadeService {
    
    @Autowired
    private CidadeRepository cidadeRepository;

    // CREATE
    @Transactional
    public Cidade create(Cidade objeto) {
        objeto.setCod_cidade(null);
        return this.cidadeRepository.save(objeto);
    }

    // READ
    public Cidade findById(Long id) {
        Optional<Cidade> cidade = this.cidadeRepository.findById(id);
        return cidade.orElseThrow(() -> new RuntimeException(
            "Cidade não encontrada!! id:" + id + ", tipo: " + Cidade.class.getName()
        ));
    }

    // UPDATE
    @Transactional
    public Cidade update(Cidade objeto) {
        Cidade cidade = findById(objeto.getCod_cidade());
        cidade = objeto;
        return this.cidadeRepository.save(cidade);
    }

    // DELETE
    public void delete(Long id) {
        findById(id);
        try {
            this.cidadeRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("[CIDADE] Não é possivel excluir! Entidades relacionadas!!");
        }
    }

    // FIND ALL
    public List<Cidade> findAllCidadesByEstadoUF(String uf) {
        List<Cidade> cidades = this.cidadeRepository.findAllCidadesByEstadoUF(uf);
        return cidades;
    }
}
