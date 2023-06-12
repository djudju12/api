package com.comidaderuadev.api.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comidaderuadev.api.entity.pedido.StatusPedido;
import com.comidaderuadev.api.exceptions.produto.NotFoundException;
import com.comidaderuadev.api.repository.StatusPedidoRepository;
import com.comidaderuadev.api.service.StatusPedidoService;

@Service
public class StatusPedidoServiceImp implements StatusPedidoService {

    @Autowired
    private StatusPedidoRepository statusPedidoRepository;

    @Override
    public List<StatusPedido> findAll() {
        return statusPedidoRepository.findAll();
    }

    @Override
    public StatusPedido findByDescricao(String descricao) {
        Optional<StatusPedido> statusPedido = statusPedidoRepository.findByDescricao(descricao);

        if (statusPedido.isPresent())
            return statusPedido.get();

        throw new NotFoundException("Status não encontrado. Status: " + descricao);

    }

    @Override
    public StatusPedido add(StatusPedido statusPedido) {
        statusPedido.setId(0);
        return statusPedidoRepository.save(statusPedido);
    }

    @Override
    public void delete(StatusPedido statusPedido) {
        statusPedidoRepository.delete(statusPedido);
    }

}
