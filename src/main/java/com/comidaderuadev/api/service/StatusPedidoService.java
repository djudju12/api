package com.comidaderuadev.api.service;

import java.util.List;

import com.comidaderuadev.api.entity.pedido.StatusPedido;

public interface StatusPedidoService {
    public List<StatusPedido> findAll();
    public StatusPedido findByDescricao(String descricao);
    public StatusPedido add(StatusPedido statusPedido);
    public void delete(StatusPedido statusPedido);
}
