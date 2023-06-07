package com.comidaderuadev.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produto_id")
    private int id;

    @Column(name = "produto_descricao", length = 200)
    private String produtoDescricao;

    @Column(name = "produto_valor")
    private double produtoValor;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    Produto() {}

    public Produto(String produtoDescricao, double produtoValor) {
        this.produtoDescricao = produtoDescricao;
        this.produtoValor = produtoValor;
    }

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

}
