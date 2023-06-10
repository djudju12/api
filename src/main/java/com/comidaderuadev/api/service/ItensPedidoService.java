package com.comidaderuadev.api.service;

import java.util.List;

import com.comidaderuadev.api.entity.pedido.ItensPedido;
import com.comidaderuadev.api.entity.pedido.Pedido;
import com.comidaderuadev.api.entity.produto.Produto;

public interface ItensPedidoService {
    // public List<ItensPedido> findAll();
    // public ItensPedido findByPedido(Pedido pedido);
    // public void removeProdutoCarrinho(Produto produto);
    // public ItensPedido addProdutosCarrinho(List<Produto> produtos);
    public List<ItensPedido> findByPedido(Pedido pedido);
}
