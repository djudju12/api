package com.comidaderuadev.api.entity.DTO;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CriaPedidoDTO {

    List<Integer> itens;

    @Size(min = 1, max = 200, message = "tipoPagamento deve conter de 1 até 200 caracteres")
    String tipoPagamento;

}
