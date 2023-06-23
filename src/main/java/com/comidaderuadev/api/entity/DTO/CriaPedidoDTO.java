package com.comidaderuadev.api.entity.DTO;

import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public class CriaPedidoDTO {

    List<Integer> itens;

    @Size(min = 1, max = 200, message = "tipoPagamento deve conter de 1 até 200 caracteres")
    String tipoPagamento;

    public List<Integer> getItens() {
        return itens;
    }

    public void setItens(List<Integer> itens) {
        if (itens == null)
            new ArrayList<>();

        this.itens = itens;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }
}
