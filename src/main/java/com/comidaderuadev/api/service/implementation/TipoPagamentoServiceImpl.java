package com.comidaderuadev.api.service.implementation;

import java.util.List;

import com.comidaderuadev.api.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comidaderuadev.api.entity.pedido.TipoPagamento;
import com.comidaderuadev.api.repository.TipoPagamentoRepository;
import com.comidaderuadev.api.service.TipoPagamentoService;

@Service
public class TipoPagamentoServiceImpl implements TipoPagamentoService{

    @Autowired
    private TipoPagamentoRepository tipoPagamentoRepository;

    @Override
    public TipoPagamento findByDescricao(String descricao) {
        return tipoPagamentoRepository
                .findByDescricao(descricao)
                .orElseThrow(() ->
                        new NotFoundException("Tipo de pagamento não encontrado: " + descricao));
    }

    @Override
    public List<TipoPagamento> findAll() {
        return tipoPagamentoRepository.findAll();
    }
}
