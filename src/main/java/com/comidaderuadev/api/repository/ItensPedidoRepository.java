package com.comidaderuadev.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.comidaderuadev.api.entity.pedido.ItensPedido;
import com.comidaderuadev.api.entity.pedido.Pedido;
import com.comidaderuadev.api.entity.produto.Produto;

public interface ItensPedidoRepository extends JpaRepository<ItensPedido, Integer> {
    public List<ItensPedido> findByPedido(Pedido pedido);

    public ItensPedido findByPedidoAndProduto(Pedido pedido, Produto produto);
}
