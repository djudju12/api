package com.comidaderuadev.api.service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comidaderuadev.api.entity.pedido.ItensPedido;
import com.comidaderuadev.api.entity.produto.Produto;
import com.comidaderuadev.api.repository.ItensPedidoRepository;
import com.comidaderuadev.api.service.ItensPedidoService;

import jakarta.transaction.Transactional;

@Service
public class ItensPedidoServiceImpl implements ItensPedidoService {


    @Autowired
    private ItensPedidoRepository itensPedidoRepository;

    @Override
    public List<ItensPedido> findAll() {
        return itensPedidoRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(int vendaId) {
        itensPedidoRepository.deleteById(vendaId);
    }

    @Transactional
    @Override
    public ItensPedido addProduto(Produto produto) {
        ItensPedido itensPedido = new ItensPedido(produto);
        return itensPedidoRepository.save(itensPedido);
    }

    @Override
    public List<ItensPedido> addProdutos(Produto produto, int quantidadeProduto) {
        List<ItensPedido> listaItens = new ArrayList<>();
        for (int i = 0; i < quantidadeProduto; i++) {
            ItensPedido itemCriado = new ItensPedido(produto);
            listaItens.add(itemCriado);
        }

        return itensPedidoRepository.saveAll(listaItens);
    }
}
