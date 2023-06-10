package com.comidaderuadev.api.service;

import java.util.List;

import com.comidaderuadev.api.entity.pedido.ItensPedido;
import com.comidaderuadev.api.entity.pedido.Pedido;
import com.comidaderuadev.api.entity.produto.Produto;

public interface PedidoService {
    public List<Pedido> findAll();
    public Pedido findById(int id);
    public Pedido add(Pedido pedido);
    public Pedido update(Pedido pedido);
    public void delete(int id);    
    public Pedido addProduto(Pedido pedido, Produto produto);
    public void removeProduto(int itensPedidoId);
}
