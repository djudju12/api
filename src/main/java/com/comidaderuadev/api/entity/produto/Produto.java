
package com.comidaderuadev.api.entity.produto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;

import java.util.Objects;

@Entity
@Table(name = "produto")
public class Produto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produto_id", unique = true, nullable = false)
    private int id;

    @Column(name = "produto_descricao", length = 200, nullable = false)
    private String produtoDescricao;

    @Column(name = "produto_valor", nullable = false)
    private double produtoValor;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    public Produto() {}

    public Produto(String produtoDescricao, double produtoValor, Categoria categoria) {
        this.produtoDescricao = produtoDescricao;
        this.produtoValor = produtoValor;
        this.categoria = categoria;
    }

    @Builder
    public Produto(int id, String produtoDescricao, double produtoValor, Categoria categoria) {
        this.id = id;
        this.produtoDescricao = produtoDescricao;
        this.produtoValor = produtoValor;
        this.categoria = categoria;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Produto [id=" + id + 
                ", produtoDescricao=" + produtoDescricao + 
                ", produtoValor=" + produtoValor+ 
                ", categoria=" + categoria + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return id == produto.id && Double.compare(produto.produtoValor, produtoValor) == 0 && Objects.equals(produtoDescricao, produto.produtoDescricao) && Objects.equals(categoria, produto.categoria);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, produtoDescricao, produtoValor, categoria);
    }
}
