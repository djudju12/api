package com.comidaderuadev.api.entity.pedido.DTO;

import java.util.ArrayList;
import java.util.List;

public class PedidoDetalhadoDTO extends PedidoDTO {

    private List<ItensPedidoDTO> itens;

    public List<ItensPedidoDTO> getItens() {
        return itens;
    }

    public void setItens(List<ItensPedidoDTO> itens) {
        this.itens = itens;
    }

    public void addItens(ItensPedidoDTO item) {
        if (this.itens == null)
            this.itens = new ArrayList<>();

        this.itens.add(item);
    }

}
