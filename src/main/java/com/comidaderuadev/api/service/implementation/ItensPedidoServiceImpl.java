package com.comidaderuadev.api.service.implementation;

import java.util.ArrayList;
import java.util.List;

import com.comidaderuadev.api.entity.pedido.Pedido;
import com.comidaderuadev.api.exceptions.NotFoundException;
import com.comidaderuadev.api.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comidaderuadev.api.entity.pedido.ItensPedido;
import com.comidaderuadev.api.repository.ItensPedidoRepository;
import com.comidaderuadev.api.service.ItensPedidoService;

import jakarta.transaction.Transactional;

@Service
public class ItensPedidoServiceImpl implements ItensPedidoService {

    @Autowired
    private ProdutoRepository produtoRepository;

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

    @Override
    @Transactional
    public List<ItensPedido> addProdutos(Pedido pedido, List<Integer> listOfIdsOfProdutos) {
        List<ItensPedido> listaItens = new ArrayList<>();

        listOfIdsOfProdutos.stream()
                .map((id) -> produtoRepository.findById(id)
                        .orElseThrow(() ->
                                new NotFoundException("Produto não encontrado. Id: " + id)))
                .forEach((produto) -> listaItens.add(new ItensPedido(pedido, produto)));

        return itensPedidoRepository.saveAll(listaItens);
    }
}
