package com.curso.repositories;

import com.curso.domains.GrupoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoProdutoRepository extends JpaRepository<GrupoProduto, Integer> {

}
