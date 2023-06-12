package com.comidaderuadev.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.comidaderuadev.api.entity.pedido.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    
}
