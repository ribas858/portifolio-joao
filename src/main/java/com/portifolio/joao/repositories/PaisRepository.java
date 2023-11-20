package com.portifolio.joao.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portifolio.joao.models.Pais;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Long> {
    
}
