package com.comidaderuadev.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comidaderuadev.api.entity.pedido.StatusPedido;
import com.comidaderuadev.api.entity.produto.Categoria;

public interface StatusPedidoRepository extends JpaRepository<StatusPedido, Integer> {
    public StatusPedido findByDescricao(String descricao);    
}
