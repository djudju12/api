package com.comidaderuadev.api.entity.produto.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

public class ProdutoDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Id do produto", example = "26")
    private int produtoId;

    @Schema(description = "Identificador do produto", example = "Pão Árabe")
    private String produtoDescricao;

    @Schema(description = "Valor do produto", example = "12.00")
    private double produtoValor;

    @Schema(description = "Nome da categoria do produto", example = "COMIDA BRASILEIRA")
    private String categoria;

    public String getProdutoDescricao() {
        return produtoDescricao;
    }

    public void setProdutoDescricao(String produtoDescricao) {
        this.produtoDescricao = produtoDescricao;
    }

    public double getProdutoValor() {
        return produtoValor;
    }

    public void setProdutoValor(double produtoValor) {
        this.produtoValor = produtoValor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(int produtoId) {
        this.produtoId = produtoId;
    }
}
