package com.portifolio.joao.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.portifolio.joao.models.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

    @Query(value = "SELECT c FROM Cidade c WHERE c.estado_cidade = :uf")
    List<Cidade> findAllCidadesByEstadoUF(@Param("uf") String uf);
    
}
