package com.comidaderuadev.api.entity.pedido;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

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

    @ManyToOne
    @JoinColumn(name = "pedido_status_id")
    private StatusPedido status;

    // @Temporal(TemporalType.DATE)
    // @Column(name = "pedido_data")
    // @GeneratedValue(strategy = GenerationType.AUTO)
    // private Date dataPedido;

    Pedido() {}

    public Pedido(TipoPagamento tipoPagamento, StatusPedido status) {
        this.tipoPagamento = tipoPagamento;
        this.status = status;
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

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    // public Date getDataPedido() {
    //     return dataPedido;
    // }

    // public void setDataPedido(Date dataPedido) {
    //     this.dataPedido = dataPedido;
    // }
    
}
