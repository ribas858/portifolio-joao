package com.portifolio.joao.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.portifolio.joao.models.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

    @Query(value = "SELECT adt.cod_admin FROM admin_telefone adt WHERE adt.cod_telefone = :id", nativeQuery = true)
    Optional<Long> find_cod_admin_by_cod_telefone(@Param("id") Long id);

    @Query(value = "SELECT ct.cod_cliente FROM cliente_telefone ct WHERE ct.cod_telefone = :id", nativeQuery = true)
    Optional<Long> find_cod_cliente_by_cod_telefone(@Param("id") Long id);
    
}
