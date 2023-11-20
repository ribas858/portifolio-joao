package com.portifolio.joao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portifolio.joao.models.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
    
}
