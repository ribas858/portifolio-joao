package com.portifolio.joao.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portifolio.joao.Utils.AdminEnderecoUtil;
import com.portifolio.joao.models.Admin;
import com.portifolio.joao.models.Cidade;
import com.portifolio.joao.models.Endereco;
import com.portifolio.joao.models.Estado;
import com.portifolio.joao.models.Pais;
import com.portifolio.joao.repositories.AdminRepository;
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

    @Autowired
    private AdminRepository adminRepository;


    // CREATE
    @Transactional
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
    @Transactional
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
        Endereco endereco = findById(id);
        try {
            Admin admin = find_admin(find_cod_admin_by_cod_endereco(id));
            if(Objects.nonNull(admin)) {
               admin.getEnderecos().remove(endereco);
            }

            this.enderecoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("[ENDERECO] Não é possivel excluir! Entidades relacionadas!!" + e);
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

    // FIND ALL : COD ADMIN
    public List<Endereco> find_enderecos_admin(Long cod_admin) {
        Admin admin = find_admin(cod_admin);
        return admin.getEnderecos();
    }

    // FIND COD ADMIN
    public Long find_cod_admin_by_cod_endereco(Long cod_endereco) {
        Optional<Long> cod_admin = this.enderecoRepository.find_cod_admin_by_cod_endereco(cod_endereco);
        return cod_admin.orElseThrow(() -> new RuntimeException(
            "Cod admin relacionado com Cod endereco não encontrado!! id:" + cod_endereco + ", tipo: " + Endereco.class.getName()
        ));
    }

    // FIND ADMIN
    public Admin find_admin(Long cod_admin) {
        Optional<Admin> admin = this.adminRepository.findById(cod_admin);
        return admin.orElseThrow(() -> new RuntimeException(
            "Admin não encontrado!! id:" + cod_admin + ", tipo: " + Admin.class.getName()
        ));
    }

    // ADD TELEFONE
    public Admin add_telefone(AdminEnderecoUtil objetos) {
        if (Objects.isNull(objetos.getCod_admin())) {
            throw new RuntimeException("[ENDERECO] Relacionamento não encontrado!!! ADMIN");
        }

        Admin admin = find_admin(objetos.getCod_admin());
        if (admin.getEnderecos().size() > 1) {
            throw new RuntimeException("[ENDERECO] Enderecos cheio!! max -> 2");
        } else {
            objetos.setEnderecos(objetos.getEnderecos().subList(0, 2 - admin.getEnderecos().size()));
            
            create(objetos.getEnderecos());
            Integer size = admin.getEnderecos().size();
            
            for (Endereco endereco : objetos.getEnderecos()) {
                admin.getEnderecos().add(endereco);
                size++;
                if (size == 2) {
                    break;
                }
            }
        }
        return admin;
    }

}
