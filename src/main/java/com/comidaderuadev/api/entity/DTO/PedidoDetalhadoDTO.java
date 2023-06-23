package com.comidaderuadev.api.entity.DTO;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class PedidoDetalhadoDTO extends PedidoDTO {

    private List<ItemPedidoDTO> itens;

    public PedidoDetalhadoDTO(List<ItemPedidoDTO> itens) {
        this.itens = itens;
    }

    public void addItens(ItemPedidoDTO item) {
        if (this.itens == null)
            this.itens = new ArrayList<>();

        this.itens.add(item);
    }

}
