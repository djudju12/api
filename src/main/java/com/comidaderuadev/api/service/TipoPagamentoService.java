package com.comidaderuadev.api.service;

import java.util.List;

import com.comidaderuadev.api.entity.pedido.TipoPagamento;

public interface TipoPagamentoService {
    public List<TipoPagamento> findAll();
    public TipoPagamento findByDescricao(String descricao);
    public TipoPagamento add(TipoPagamento tipoPagamento);
    public void delete(TipoPagamento tipoPagamento);
}
