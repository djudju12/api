package com.comidaderuadev.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.comidaderuadev.api.entity.pedido.ItensPedido;
import com.comidaderuadev.api.entity.pedido.Pedido;

public interface ItensPedidoRepository extends JpaRepository<ItensPedido, Integer> {
    public List<ItensPedido> findByPedido(Pedido pedido);

    @Query("DELETE FROM ItensPedido i WHERE i.pedido.id = :pedidoId AND i.produto.id = :produtoId")
    @Modifying
    public void removeByPedidoIdAndProdutoId(@Param("pedidoId") int pedidoId, @Param("produtoId") int produtoId);
}
