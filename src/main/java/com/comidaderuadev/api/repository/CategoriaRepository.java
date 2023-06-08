package com.comidaderuadev.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comidaderuadev.api.entity.produto.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    
}
