package com.comidaderuadev.api.entity.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
public class PedidoDTO {

    @JsonProperty("idPedido")
    private int id;
    private String tipoPagamento;
    private LocalDateTime pedidoData;

    public PedidoDTO() {}

    @Override
    public String toString() {
        return "PedidoDTO{" +
                "pedidoId=" + id +
                ", pedidoTipoPagamento='" + tipoPagamento + '\'' +
                ", pedidoData=" + pedidoData +
                '}';
    }
}
