package com.comidaderuadev.api.entity.DTO;

public class ProdutoDTO {
    private int id;
    private String produtoDescricao;
    private double produtoValor;
    private int categoria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria_id(int categoria) {
        this.categoria = categoria;
    }
}
