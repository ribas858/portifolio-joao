package com.portifolio.joao.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portifolio.joao.Utils.AdminClienteUtil;
import com.portifolio.joao.Utils.TelefoneUtil;
import com.portifolio.joao.models.Admin;
import com.portifolio.joao.models.Cliente;
import com.portifolio.joao.models.Telefone;
import com.portifolio.joao.repositories.AdminRepository;
import com.portifolio.joao.repositories.ClienteRepository;
import com.portifolio.joao.repositories.TelefoneRepository;

@Service
public class TelefoneService {

    @Autowired
    private TelefoneRepository telefoneRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    // CREATE
    @Transactional
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
    @Transactional
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
        Telefone telefone = findById(id);
        try {

            Optional<Long> cod_admin = find_cod_admin_by_cod_telefone(id);

            if(cod_admin.isPresent()) {
                Admin admin = find_admin(cod_admin.get());
                if(Objects.nonNull(admin)) {
                admin.getTelefones().remove(telefone);
                }
            }

            Optional<Long> cod_cliente = find_cod_cliente_by_cod_telefone(id);

            if(cod_cliente.isPresent()) {
                Cliente cliente = find_cliente(cod_cliente.get());
                if(Objects.nonNull(cliente)) {
                cliente.getTelefones().remove(telefone);
                }
            }

            this.telefoneRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("[TELEFONE] Não é possivel excluir! Entidades relacionadas!!" + e);
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

    // FIND ALL : BY COD ADMIN
    public List<Telefone> find_telefones_admin(Long cod_admin) {
        Admin admin = find_admin(cod_admin);
        return admin.getTelefones();
    }

    // FIND ALL : BY COD CLIENTE
    public List<Telefone> find_telefones_cliente(Long cod_cliente) {
        Cliente cliente = find_cliente(cod_cliente);
        return cliente.getTelefones();
    }

    // FIND COD ADMIN
    public Optional<Long> find_cod_admin_by_cod_telefone(Long cod_telefone) {
        Optional<Long> cod_admin = this.telefoneRepository.find_cod_admin_by_cod_telefone(cod_telefone);
        return cod_admin;
    }

    // FIND COD CLIENTE
    public Optional<Long> find_cod_cliente_by_cod_telefone(Long cod_telefone) {
        Optional<Long> cod_cliente = this.telefoneRepository.find_cod_cliente_by_cod_telefone(cod_telefone);
        return cod_cliente;
    }

    // FIND ADMIN
    public Admin find_admin(Long cod_admin) {
        Optional<Admin> admin = this.adminRepository.findById(cod_admin);
        return admin.orElseThrow(() -> new RuntimeException(
            "Admin não encontrado!! id:" + cod_admin + ", tipo: " + Admin.class.getName()
        ));
    }
    
    // FIND CLIENTE
    public Cliente find_cliente(Long cod_cliente) {
        Optional<Cliente> cliente = this.clienteRepository.findById(cod_cliente);
        return cliente.orElseThrow(() -> new RuntimeException(
            "Cliente não encontrado!! id:" + cod_cliente + ", tipo: " + Cliente.class.getName()
        ));
    }

    // ADD TELEFONE
    public AdminClienteUtil add_telefone(TelefoneUtil objetos) {

        AdminClienteUtil adminCliente = new AdminClienteUtil();
        adminCliente.setAdmin(null);
        adminCliente.setCliente(null);

        if (Objects.nonNull(objetos.getCod_admin()) && Objects.nonNull(objetos.getCod_cliente())) {
            throw new RuntimeException("[TELEFONE] Apenas 1 relacionamento permitido!!! ADMIN ou CLIENTE");
        }

        if (Objects.nonNull(objetos.getCod_admin())) {

            Admin admin = find_admin(objetos.getCod_admin());
            Integer size = admin.getTelefones().size();

            if (size > 1) {
                throw new RuntimeException("[TELEFONE ADMIN] Telefones cheio!! max -> 2");
            } else {
                objetos.setTelefones(objetos.getTelefones().subList(0, 2 - size));
                
                create(objetos.getTelefones());
                
                for (Telefone telefone : objetos.getTelefones()) {
                    admin.getTelefones().add(telefone);
                    size++;
                    if (size == 2) {
                        break;
                    }
                }
                adminCliente.setAdmin(admin);
            }
        } else if (Objects.nonNull(objetos.getCod_cliente())) {

            Cliente cliente = find_cliente(objetos.getCod_cliente());
            Integer size = cliente.getTelefones().size();

            if (size > 1) {
                throw new RuntimeException("[TELEFONE CLIENTE] Telefones cheio!! max -> 2");
            } else {
                objetos.setTelefones(objetos.getTelefones().subList(0, 2 - size));
                
                create(objetos.getTelefones());
                
                for (Telefone telefone : objetos.getTelefones()) {
                    cliente.getTelefones().add(telefone);
                    size++;
                    if (size == 2) {
                        break;
                    }
                }
                adminCliente.setCliente(cliente);
            }
        } else {
            throw new RuntimeException("[TELEFONE] Não é possivel criar telefone! Relacionamento nulo!!!");
        }

        return adminCliente;
    }

}
