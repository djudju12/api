package com.comidaderuadev.api.service.implementation;

import java.util.List;

import com.comidaderuadev.api.exceptions.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comidaderuadev.api.entity.pedido.Pedido;
import com.comidaderuadev.api.repository.ItensPedidoRepository;
import com.comidaderuadev.api.repository.PedidoRepository;
import com.comidaderuadev.api.repository.ProdutoRepository;
import com.comidaderuadev.api.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService {


    @Autowired
    private PedidoRepository pedidoRepository;

    @Override
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido findById(int id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pedido nao encontrado. Id: " + id));
    }

    @Override
    public void delete(int id) {
        pedidoRepository.deleteById(id);
    }
}
