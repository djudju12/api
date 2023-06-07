package com.comidaderuadev.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comidaderuadev.api.entity.StatusPedido;

public interface StatusPedidoRepository extends JpaRepository<StatusPedido, Integer> {
    
}
