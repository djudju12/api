package com.comidaderuadev.api.service;

import java.util.List;

import com.comidaderuadev.api.entity.pedido.Pedido;
import com.comidaderuadev.api.entity.produto.Produto;

public interface PedidoService {
    List<Pedido> findAll();
    Pedido findById(int id);
    void delete(int id);
}
