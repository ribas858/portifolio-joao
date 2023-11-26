package com.portifolio.joao.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.portifolio.joao.models.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    @Query(value = "SELECT c FROM Cliente c WHERE c.cliente_to_admin.cod_admin = :id")
    List<Cliente> findAllClientesByCod_admin(@Param("id") Long id);
}
