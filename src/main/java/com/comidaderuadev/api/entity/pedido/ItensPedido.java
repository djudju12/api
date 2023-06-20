package com.comidaderuadev.api.entity.pedido;


import com.comidaderuadev.api.entity.produto.Produto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

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

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    ItensPedido() {}

    public ItensPedido(Pedido pedido, Produto produto) {
        this.produto = produto;
        this.pedido = pedido;
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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItensPedido that = (ItensPedido) o;
        return vendaId == that.vendaId && Objects.equals(produto, that.produto) && Objects.equals(pedido, that.pedido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vendaId, produto, pedido);
    }

    @Override
    public String toString() {
        return "ItensPedido{" +
                "vendaId=" + vendaId +
                ", produto=" + produto +
                ", pedido=" + pedido +
                '}';
    }
}
