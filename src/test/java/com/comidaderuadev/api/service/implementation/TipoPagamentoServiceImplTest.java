package com.comidaderuadev.api.service.implementation;

import com.comidaderuadev.api.entity.pedido.TipoPagamento;
import com.comidaderuadev.api.entity.produto.Categoria;
import com.comidaderuadev.api.repository.TipoPagamentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class TipoPagamentoServiceImplTest {

    @Mock
    TipoPagamentoRepository tipoPagamentoRepository;

    @InjectMocks
    TipoPagamentoServiceImpl tipoPagamentoService;
    final String DESC = "PIX";

    @Test
    void findByDescricao() {
        //given
        given(tipoPagamentoRepository.findByDescricao(DESC))
                .willReturn(Optional.of(new TipoPagamento(DESC)));

        //when
        TipoPagamento returnedTipopagamento = tipoPagamentoService.findByDescricao(DESC);

        //then
        then(tipoPagamentoRepository).should().findByDescricao(DESC);
        assertThat(returnedTipopagamento).isNotNull();
        assertThat(returnedTipopagamento.getDescricao()).isEqualTo(DESC);
    }

    @Test
    void findAll() {
        //given
        List<TipoPagamento> tipoPagamentos = new ArrayList<>();
        given(tipoPagamentoRepository.findAll()).willReturn(tipoPagamentos);
        tipoPagamentos.add(mock(TipoPagamento.class));
        tipoPagamentos.add(mock(TipoPagamento.class));

        //when
        List<TipoPagamento> returnedTipoPagamentos = tipoPagamentoService.findAll();

        //then
        then(tipoPagamentoRepository).should().findAll();
        assertThat(returnedTipoPagamentos).isNotNull();
        assertThat(returnedTipoPagamentos.size()).isEqualTo(tipoPagamentos.size());
    }
}