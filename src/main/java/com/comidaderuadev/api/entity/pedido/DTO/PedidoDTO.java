package com.comidaderuadev.api.entity.pedido.DTO;

import java.util.Date;
import java.util.List;

public class PedidoDTO {

    private int pedidoId;
    private String pedidoTipoPagamento;
    private String pedidoStatus;
    private Date pedidoData;
    private List<ItensPedidoDTO> itens;

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
    public Date getPedidoData() {
        return pedidoData;
    }
    public void setPedidoData(Date pedidoData) {
        this.pedidoData = pedidoData;
    }
    public List<ItensPedidoDTO> getItens() {
        return itens;
    }
    public void setItens(List<ItensPedidoDTO> itens) {
        this.itens = itens;
    }

}
