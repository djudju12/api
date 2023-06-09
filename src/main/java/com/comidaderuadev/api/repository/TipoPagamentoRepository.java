package com.comidaderuadev.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comidaderuadev.api.entity.pedido.TipoPagamento;

public interface TipoPagamentoRepository extends JpaRepository<TipoPagamento, Integer> {
    public TipoPagamento findByDescricao(String descricao);    
}
