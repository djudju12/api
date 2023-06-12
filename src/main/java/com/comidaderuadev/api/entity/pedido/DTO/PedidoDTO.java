package com.comidaderuadev.api.entity.pedido.DTO;

import java.time.LocalDateTime;
import java.util.Date;

public class PedidoDTO {

    private int pedidoId;
    private String pedidoTipoPagamento;
    private String pedidoStatus;
    private LocalDateTime pedidoData;

    public int getPedidoId() {
        return pedidoId;
    }
    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }
    public String getPedidoTipoPagamento() {
        return pedidoTipoPagamento;
    }
    public void setPedidoTipoPagamento(String pedidoTipoPagamento) {
        this.pedidoTipoPagamento = pedidoTipoPagamento;
    }
    public String getPedidoStatus() {
        return pedidoStatus;
    }
    public void setPedidoStatus(String pedidoStatus) {
        this.pedidoStatus = pedidoStatus;
    }
    public LocalDateTime getPedidoData() {
        return pedidoData;
    }
    public void setPedidoData(LocalDateTime pedidoData) {
        this.pedidoData = pedidoData;
    }


}
