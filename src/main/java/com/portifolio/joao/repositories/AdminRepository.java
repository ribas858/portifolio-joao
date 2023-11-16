package com.portifolio.joao.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portifolio.joao.models.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, UUID> {

    
    
}
