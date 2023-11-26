package com.portifolio.joao.repositories;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.portifolio.joao.models.Admin;
import com.portifolio.joao.models.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    @Query(value = "SELECT ade.cod_admin FROM admin_endereco ade WHERE ade.cod_endereco = :id", nativeQuery = true)
    Optional<Long> find_cod_admin_by_cod_endereco(@Param("id") Long id);

    @Query(value = "SELECT a FROM Admin a WHERE a.cod_admin = :id")
    Optional<Admin> find_admin(@Param("id") Long id);

    @Query(value = "SELECT ade.cod_endereco FROM admin_endereco ade WHERE ade.cod_admin = :id", nativeQuery = true)
    Optional<List<Long>> find_all_ids_endereco_by_cod_admin(@Param("id") Long id);

    // @Query(value = "SELECT * FROM imagem i WHERE i.cod_categoria = :id ", nativeQuery = true)
    // List<Imagem> findByCategoria_id3(@Param("id") UUID id);

}
