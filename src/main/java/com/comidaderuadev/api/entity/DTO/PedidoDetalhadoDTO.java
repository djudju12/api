package com.comidaderuadev.api.entity.DTO;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PedidoDetalhadoDTO extends PedidoDTO {

    private List<ItemPedidoDTO> itens;

    public PedidoDetalhadoDTO(List<ItemPedidoDTO> itens) {
        this.itens = itens;
    }

    public PedidoDetalhadoDTO(int pedidoId, String pedidoTipoPagamento, LocalDateTime pedidoData, List<ItemPedidoDTO> itens) {
        super(pedidoId, pedidoTipoPagamento, pedidoData);
        this.itens = itens;
    }


    public void addItens(ItemPedidoDTO item) {
        if (this.itens == null)
            this.itens = new ArrayList<>();

        this.itens.add(item);
    }

}
