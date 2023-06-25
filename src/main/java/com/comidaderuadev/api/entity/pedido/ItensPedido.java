package com.comidaderuadev.api.entity.pedido;


import com.comidaderuadev.api.entity.produto.Produto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

@Entity
@Table(name = "pedido_itens")
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
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

    public ItensPedido(Pedido pedido, Produto produto) {
        this.produto = produto;
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
    public String toString() {
        return "ItensPedido{" +
                "vendaId=" + vendaId +
                ", produto=" + produto +
                ", pedido=" + pedido +
                '}';
    }
}
