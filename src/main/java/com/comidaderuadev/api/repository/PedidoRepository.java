package com.comidaderuadev.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comidaderuadev.api.entity.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    
}
