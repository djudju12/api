package com.comidaderuadev.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comidaderuadev.api.entity.TipoPagamento;

public interface TipoPagamentoRepository extends JpaRepository<TipoPagamento, Integer> {
    
}
