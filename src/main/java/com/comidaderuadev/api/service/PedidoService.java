package com.comidaderuadev.api.service;

import java.util.List;

import com.comidaderuadev.api.entity.pedido.Pedido;

public interface PedidoService {
    public List<Pedido> findAll();
    public Pedido findById(int id);
    public Pedido add(Pedido pedido);
    public Pedido update(Pedido pedido);
    public void delete(int id);    
}
