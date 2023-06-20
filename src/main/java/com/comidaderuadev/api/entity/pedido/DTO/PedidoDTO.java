package com.comidaderuadev.api.entity.pedido.DTO;

import lombok.Builder;

import java.time.LocalDateTime;

public class PedidoDTO {

    private int idPedido;
    private String tipoPagamento;
    private LocalDateTime pedidoData;

    public PedidoDTO() {}

    @Builder
    public PedidoDTO(int pedidoId, String pedidoTipoPagamento, LocalDateTime pedidoData) {
        this.idPedido = pedidoId;
        this.tipoPagamento = pedidoTipoPagamento;
        this.pedidoData = pedidoData;
    }

    public int getIdPedido() {
        return idPedido;
    }
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
    public String getTipoPagamento() {
        return tipoPagamento;
    }
    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }
    public LocalDateTime getPedidoData() {
        return pedidoData;
    }
    public void setPedidoData(LocalDateTime pedidoData) {
        this.pedidoData = pedidoData;
    }

    @Override
    public String toString() {
        return "PedidoDTO{" +
                "pedidoId=" + idPedido +
                ", pedidoTipoPagamento='" + tipoPagamento + '\'' +
                ", pedidoData=" + pedidoData +
                '}';
    }
}
