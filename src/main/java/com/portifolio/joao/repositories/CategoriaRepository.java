package com.portifolio.joao.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.portifolio.joao.models.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
    @Query(value = "SELECT c FROM Categoria c WHERE c.categoria_to_admin.cod_admin = :id")
    List<Categoria> findAllCategoriaByCod_admin(@Param("id") Long id);
}
