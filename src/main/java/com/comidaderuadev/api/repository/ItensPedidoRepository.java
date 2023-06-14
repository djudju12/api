package com.comidaderuadev.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comidaderuadev.api.entity.pedido.ItensPedido;

public interface ItensPedidoRepository extends JpaRepository<ItensPedido, Integer> {
}
