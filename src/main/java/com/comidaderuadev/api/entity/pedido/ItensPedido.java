package com.comidaderuadev.api.entity.pedido;


import com.comidaderuadev.api.entity.produto.Produto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "pedido_itens")
public class ItensPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_itens_id", nullable = false, updatable = false, unique = true)
    private int vendaId;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    public ItensPedido() {
    }

    public ItensPedido(Produto produto) {
        this.produto = produto;
    }


    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getVendaId() {
        return vendaId;
    }

    public void setVendaId(int vendaId) {
        this.vendaId = vendaId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItensPedido that = (ItensPedido) o;
        return vendaId == that.vendaId && Objects.equals(produto, that.produto);
    }

}
