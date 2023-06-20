package com.comidaderuadev.api.entity.pedido;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido")
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "tipo_pagamento_id")
    private TipoPagamento tipoPagamento;

    @Column(name = "pedido_data")
    @CreationTimestamp
    private LocalDateTime pedidoData;

    @OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY)
    private List<ItensPedido> itens;

    Pedido() {}

    public Pedido(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
        this.itens = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }


    public List<ItensPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItensPedido> itens) {
        this.itens = itens;
    }

    public void addProduto(ItensPedido item) {
        itens.add(item);
    }

    public LocalDateTime getPedidoData() {
        return pedidoData;
    }

    public void setPedidoData(LocalDateTime pedidoData) {
        this.pedidoData = pedidoData;
    }
}
