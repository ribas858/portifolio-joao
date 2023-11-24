package com.portifolio.joao.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public List<Endereco> create(List<Endereco> objetosEnderecos) {
        //System.out.printf("===========================================----------------------\n\n");
        //System.out.print("bairro: " + objetosEnderecos.get(0).getBairro() + " id:" + objetosEnderecos.get(0).getCod_endereco() + "\n");
        for (int i = 0; i < objetosEnderecos.size(); i++) {
            Cidade Cidade = this.cidadeService.findById(objetosEnderecos.get(i).getEndereco_to_cidade().getCod_cidade());
            Estado estado = this.estadoService.findById(objetosEnderecos.get(i).getEndereco_to_estado().getCod_estado());
            Pais pais = this.paisService.findById(objetosEnderecos.get(i).getEndereco_to_pais().getCod_pais());
            objetosEnderecos.get(i).setCod_endereco(null);
            objetosEnderecos.get(i).setEndereco_to_cidade(Cidade);
            objetosEnderecos.get(i).setEndereco_to_estado(estado);
            objetosEnderecos.get(i).setEndereco_to_pais(pais);
        }
        return this.enderecoRepository.saveAll(objetosEnderecos);
    }

    // READ
    public Endereco findById(Long id) {
        Optional<Endereco> endereco = this.enderecoRepository.findById(id);
        return endereco.orElseThrow(() -> new RuntimeException(
            "Endereco não encontrado!! id:" + id + ", tipo: " + Endereco.class.getName()
        ));
    }

    // UPDATE
    public List<Endereco> update(List<Endereco> objetosEnderecos) {
        List<Endereco> enderecos = new ArrayList<Endereco>();
        for (Endereco obj : objetosEnderecos ) {
            findById(obj.getCod_endereco());
            enderecos.add(obj);
            
        }
        return this.enderecoRepository.saveAll(enderecos);
    }

    // DELETE
    public void delete(Long id) {
        findById(id);
        try {
            this.enderecoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("[ENDERECO] Não é possivel excluir! Entidades relacionadas!!");
        }
    }

    // DELETE ALL
    public void deleteAllByIds(List<Long> ids) {
        for (Long i : ids) {
            findById(i);
        }
        try {
            this.enderecoRepository.deleteAllByIdInBatch(ids);
        } catch (Exception e) {
            throw new RuntimeException("[ENDERECO LISTA] Não é possivel excluir! Entidades relacionadas!!");
        }
    }

}
