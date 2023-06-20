package com.comidaderuadev.api.entity.pedido.DTO;

import java.util.ArrayList;
import java.util.List;

public class CriaPedidoDTO {
    List<Integer> listOfIds;
    String tipoPagamento;

    public List<Integer> getListOfIds() {
        return listOfIds;
    }

    public void setListOfIds(List<Integer> listOfIds) {
        if (listOfIds == null)
            new ArrayList<>();

        this.listOfIds = listOfIds;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }
}
