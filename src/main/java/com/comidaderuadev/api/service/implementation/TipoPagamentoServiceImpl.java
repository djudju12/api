package com.comidaderuadev.api.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comidaderuadev.api.entity.pedido.TipoPagamento;
import com.comidaderuadev.api.exceptions.produto.NotFoundException;
import com.comidaderuadev.api.repository.TipoPagamentoRepository;
import com.comidaderuadev.api.service.TipoPagamentoService;

@Service
public class TipoPagamentoServiceImpl implements TipoPagamentoService{

    @Autowired
    private TipoPagamentoRepository tipoPagamentoRepository;

    @Override
    public List<TipoPagamento> findAll() {
        return tipoPagamentoRepository.findAll();
    }

    @Override
    public TipoPagamento findByDescricao(String descricao) {
        Optional<TipoPagamento> tipoPagamento = tipoPagamentoRepository.findByDescricao(descricao);
        
        if (tipoPagamento.isPresent())
            return tipoPagamento.get();

        throw new NotFoundException("Tipo de pagamento não encontrado. Tipo de pagamento: " + descricao);
    }

    @Override
    public TipoPagamento add(TipoPagamento tipoPagamento) {
        tipoPagamento.setId(0);;
        return tipoPagamentoRepository.save(tipoPagamento);
    }

    @Override
    public void delete(TipoPagamento tipoPagamento) {
        tipoPagamentoRepository.delete(tipoPagamento);
    }
    
}
