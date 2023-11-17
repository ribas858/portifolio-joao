package com.portifolio.joao.services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portifolio.joao.models.Cidade;
import com.portifolio.joao.models.Endereco;
import com.portifolio.joao.models.Estado;
import com.portifolio.joao.models.Pais;
import com.portifolio.joao.repositories.EnderecoRepository;

@Service
public class EnderecoService {
    
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private EstadoService estadoService;

    @Autowired
    private PaisService paisService;

    // CREATE
    public Endereco create(Endereco objeto) {
        Cidade Cidade = this.cidadeService.findById(objeto.getEndereco_to_cidade().getCod_cidade());
        Estado estado = this.estadoService.findById(objeto.getEndereco_to_estado().getCod_estado());
        Pais pais = this.paisService.findById(objeto.getEndereco_to_pais().getCod_pais());
        objeto.setCod_endereco(null);
        objeto.setEndereco_to_cidade(Cidade);
        objeto.setEndereco_to_estado(estado);
        objeto.setEndereco_to_pais(pais);
        return this.enderecoRepository.save(objeto);
    }

    // READ
    public Endereco findById(UUID id) {
        Optional<Endereco> endereco = this.enderecoRepository.findById(id);
        return endereco.orElseThrow(() -> new RuntimeException(
            "Endereco não encontrado!! id:" + id + ", tipo: " + Endereco.class.getName()
        ));
    }

    // UPDATE
    public Endereco update(Endereco objeto) {
        Endereco endereco = findById(objeto.getCod_endereco());
        endereco = objeto;
        return this.enderecoRepository.save(endereco);
    }

    // DELETE
    public void delete(UUID id) {
        findById(id);
        try {
            this.enderecoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("[ENDERECO] Não é possivel excluir! Entidades relacionadas!!");
        }
    }

}
