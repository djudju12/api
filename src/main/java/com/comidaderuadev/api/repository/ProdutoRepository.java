package com.comidaderuadev.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comidaderuadev.api.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    
}
