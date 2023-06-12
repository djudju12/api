package com.comidaderuadev.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comidaderuadev.api.entity.pedido.StatusPedido;

public interface StatusPedidoRepository extends JpaRepository<StatusPedido, Integer> {
    public Optional<StatusPedido> findByDescricao(String descricao);    
}
