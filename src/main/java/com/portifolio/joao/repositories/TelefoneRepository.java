package com.portifolio.joao.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portifolio.joao.models.Telefone;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, UUID> {
    
}
