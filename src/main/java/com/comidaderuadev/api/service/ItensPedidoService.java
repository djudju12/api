package com.comidaderuadev.api.service;

import java.util.List;

import com.comidaderuadev.api.entity.pedido.ItensPedido;
import com.comidaderuadev.api.entity.produto.Produto;

public interface ItensPedidoService {
    public List<ItensPedido> findAll();
    public void deleteById(int vendaId);
    public ItensPedido addProduto(Produto produto);
    public List<ItensPedido> addProdutos(Produto produto, int quantidadeProduto);
}
