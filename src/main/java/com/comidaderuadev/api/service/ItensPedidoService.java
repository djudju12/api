package com.comidaderuadev.api.service;

import java.util.List;

import com.comidaderuadev.api.entity.pedido.ItensPedido;
import com.comidaderuadev.api.entity.pedido.Pedido;
import com.comidaderuadev.api.entity.produto.Produto;

public interface ItensPedidoService {
    public List<ItensPedido> findAll();
    public void deleteById(int vendaId);
    List<ItensPedido> addProdutos(Pedido pedido, List<Integer> listOfIdsOfProdutos);
}
