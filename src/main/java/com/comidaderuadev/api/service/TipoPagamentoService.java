package com.comidaderuadev.api.service;

import java.util.List;

import com.comidaderuadev.api.entity.pedido.TipoPagamento;

public interface TipoPagamentoService {

    public TipoPagamento findByDescricao(String descricao);
    public List<TipoPagamento> findAll();
}
