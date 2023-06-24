package com.comidaderuadev.api.service.implementation;

import com.comidaderuadev.api.entity.pedido.Pedido;
import com.comidaderuadev.api.entity.pedido.TipoPagamento;
import com.comidaderuadev.api.exceptions.NotFoundException;
import com.comidaderuadev.api.repository.PedidoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoServiceImplTest {


    @Mock
    private PedidoRepository pedidoRepository;

    @InjectMocks
    private PedidoServiceImpl pedidoService;

    @Captor
    private ArgumentCaptor<Integer> integerArgumentCaptor;
    
    @Test
    void findAll() {
        //given
        List<Pedido> expectedPedidos = new ArrayList<>();
        expectedPedidos.add(mock(Pedido.class));
        given(pedidoRepository.findAll()).willReturn(expectedPedidos);

        //when
        List<Pedido> returnedList = pedidoService.findAll();

        //then
        then(pedidoRepository).should(times(1)).findAll();
        assertThat(returnedList.size()).isEqualTo(1);
        assertThat(returnedList).isEqualTo(expectedPedidos);
        then(pedidoRepository).shouldHaveNoMoreInteractions();

    }

    @Test
    void findByIdSuccess() {
        //given
        Pedido expectedPedido = Pedido.builder()
                                .id(1)
                                .tipoPagamento(new TipoPagamento("PIX"))
                                .build();

        given(pedidoRepository.findById(anyInt())).willReturn(Optional.of(expectedPedido));

        //when
        Pedido returnedPedido = pedidoRepository.findById(1).orElse(null);
            
        //then
        verify(pedidoRepository).findById(integerArgumentCaptor.capture());
        then(pedidoRepository).should().findById(integerArgumentCaptor.getValue());
        then(pedidoRepository).shouldHaveNoMoreInteractions();
        assertThat(returnedPedido).isEqualTo(expectedPedido);
    }

    @Test
    void findByIdError() {
        //given
        given(pedidoRepository.findById(anyInt())).willReturn(Optional.empty());

        //when
        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> pedidoService.findById(1));

        //then
        then(pedidoRepository).should().findById(1);
        then(pedidoRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void delete() {
        //when
        pedidoService.delete(1);

        //then
        verify(pedidoRepository).deleteById(integerArgumentCaptor.capture());
        assertThat(integerArgumentCaptor.getValue()).isEqualTo(1);
    }
}