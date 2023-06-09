package com.comidaderuadev.api.entity.pedido.DTO;

import java.util.List;

import com.comidaderuadev.api.entity.produto.Produto;

public class ItensPedidoDTO {
    private int id;
    private int pedidoId;
    private List<Produto> produtos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

}
