package com.portifolio.joao.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.portifolio.joao.models.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query(value = "SELECT ade.cod_admin FROM admin_endereco ade WHERE ade.cod_endereco = :id", nativeQuery = true)
    Optional<Long> find_cod_admin_by_cod_endereco(@Param("id") Long id);

}
