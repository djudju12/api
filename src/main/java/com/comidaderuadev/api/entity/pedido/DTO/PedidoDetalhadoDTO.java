package com.comidaderuadev.api.entity.pedido.DTO;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoDetalhadoDTO extends PedidoDTO {

    private List<ItemPedidoDTO> itens;

    public List<ItemPedidoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoDTO> itens) {
        this.itens = itens;
    }

    public void addItens(ItemPedidoDTO item) {
        if (this.itens == null)
            this.itens = new ArrayList<>();

        this.itens.add(item);
    }

}
