package com.comidaderuadev.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.comidaderuadev.api.entity.Pedido;
import com.comidaderuadev.api.repository.PedidoRepository;

@RestController
public class PedidoController {

    private PedidoRepository pedidoRepository;

    @Autowired
    public PedidoController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @PostMapping("/pedido")
    public Pedido createPedido(@RequestBody Pedido pedido) {
        pedido.setId(0);
        return pedidoRepository.save(pedido);
    }
}
