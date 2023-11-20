package com.portifolio.joao.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portifolio.joao.models.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    
}
