package com.comidaderuadev.api.entity.pedido.DTO;

import com.comidaderuadev.api.entity.produto.DTO.ProdutoDTO;

public class ItensPedidoDTO {

    private ProdutoDTO produto;
    
    private int id;


    public int getPedidoId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProdutoDTO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoDTO produto) {
        this.produto = produto;
    }

}
