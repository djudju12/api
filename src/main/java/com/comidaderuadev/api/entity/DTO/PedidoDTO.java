package com.comidaderuadev.api.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PedidoDTO {
    private int id;
    private String tipoPagamento;
    private LocalDateTime pedidoData;

    public PedidoDTO() {}

    @Builder
    public PedidoDTO(int id, String tipoPagamento, LocalDateTime pedidoData) {
        this.id = id;
        this.tipoPagamento = tipoPagamento;
        this.pedidoData = pedidoData;
    }

    @Override
    public String toString() {
        return "PedidoDTO{" +
                "pedidoId=" + id +
                ", pedidoTipoPagamento='" + tipoPagamento + '\'' +
                ", pedidoData=" + pedidoData +
                '}';
    }
}
