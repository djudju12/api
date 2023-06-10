package com.comidaderuadev.api.entity.pedido.DTO;

import java.util.ArrayList;
import java.util.List;

import com.comidaderuadev.api.entity.pedido.Pedido;
import com.comidaderuadev.api.entity.produto.Produto;

public class ItensPedidoDTO {

    private Pedido pedido;

    private List<Produto> produtos;

    public ItensPedidoDTO(Pedido pedido, List<Produto> produtos) {
        this.pedido = pedido;
        this.produtos = produtos;
    }

    public ItensPedidoDTO(Pedido pedido) {
        this.pedido = pedido;
        this.produtos = new ArrayList<>();
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public void addProduto(Produto produto) {
        this.produtos.add(produto);
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

}
