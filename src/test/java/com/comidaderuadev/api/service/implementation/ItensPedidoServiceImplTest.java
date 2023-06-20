package com.comidaderuadev.api.service.implementation;

import com.comidaderuadev.api.entity.pedido.ItensPedido;
import com.comidaderuadev.api.repository.ItensPedidoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItensPedidoServiceImplTest {

    @Mock
    ItensPedidoRepository repository;

    @InjectMocks
    ItensPedidoServiceImpl service;

    @Captor
    ArgumentCaptor<List<ItensPedido>> captor;

    List<ItensPedido> itensPedidoList = new ArrayList<>();

    @Test
    void findAll() {
        //given
        given(repository.findAll()).willReturn(itensPedidoList);
        itensPedidoList.add(mock(ItensPedido.class));

        //when
        List<ItensPedido> returnedList = service.findAll();

        //then
        then(repository).should().findAll();
        assertThat(returnedList).isNotNull();
        assertThat(returnedList.size()).isEqualTo(1);
    }

    @Test
    void deleteById() {
        //when
        service.deleteById(1);

        //then
        then(repository).should().deleteById(1);
        then(repository).shouldHaveNoMoreInteractions();
    }

}