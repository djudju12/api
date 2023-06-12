package com.comidaderuadev.api.service;

import java.util.List;

import com.comidaderuadev.api.entity.pedido.ItensPedido;
import com.comidaderuadev.api.entity.pedido.Pedido;

public interface ItensPedidoService {
    public List<ItensPedido> findByPedido(Pedido pedido);
    public void removeProdutoCarrinho(int pedidoId, int produtoId);
}
