package com.comidaderuadev.api.entity.pedido;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
@NoArgsConstructor
@Getter
@Setter
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

    @Builder
    public Pedido(int id, TipoPagamento tipoPagamento, LocalDateTime pedidoData, List<ItensPedido> itens) {
        this.id = id;
        this.tipoPagamento = tipoPagamento;
        this.pedidoData = pedidoData;
        this.itens = itens;
    }

    public Pedido(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
        this.itens = new ArrayList<>();
    }

    public void addProduto(ItensPedido item) {
        itens.add(item);
    }

}
