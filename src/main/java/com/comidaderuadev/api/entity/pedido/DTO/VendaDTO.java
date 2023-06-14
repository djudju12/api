package com.comidaderuadev.api.entity.pedido.DTO;

import com.comidaderuadev.api.entity.produto.DTO.ProdutoDTO;

import io.swagger.v3.oas.annotations.media.Schema;

public class VendaDTO {

    @Schema(description = "Informações de um produto")
    private ProdutoDTO produto;

    @Schema(description = "Id da venda", example = "26")
    private int vendaId;
    
    public ProdutoDTO getProduto() {
        return produto;
    }
    public void setProduto(ProdutoDTO produto) {
        this.produto = produto;
    }
    public int getVendaId() {
        return vendaId;
    }
    public void setVendaId(int vendaId) {
        this.vendaId = vendaId;
    }

    

}
