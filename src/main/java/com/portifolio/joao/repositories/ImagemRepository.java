package com.portifolio.joao.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.portifolio.joao.models.Imagem;

@Repository
public interface ImagemRepository extends JpaRepository<Imagem, Long> {

    // List<Imagem> findByImagem_to_categoria_Id(UUID id);

    // @Query(value = "SELECT i FROM Imagem i WHERE i.imagem_to_categoria.id = :id")
    // List<Imagem> findByCategoria_id2(@Param("id") Long id);

    @Query(value = "SELECT i FROM Imagem i WHERE i.imagem_to_categoria.cod_categoria = :id")
    List<Imagem> findAllImagensByCod_categoria(@Param("id") Long id);

    // @Query(value = "SELECT * FROM imagem i WHERE i.cod_categoria = :id ", nativeQuery = true)
    // List<Imagem> findByCategoria_id3(@Param("id") UUID id);
}