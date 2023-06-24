package com.comidaderuadev.api.service.implementation;

import com.comidaderuadev.api.entity.pedido.ItensPedido;
import com.comidaderuadev.api.entity.pedido.Pedido;
import com.comidaderuadev.api.entity.produto.Produto;
import com.comidaderuadev.api.repository.ItensPedidoRepository;
import com.comidaderuadev.api.repository.ProdutoRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItensPedidoServiceImplTest {

    @Mock
    ItensPedidoRepository itensPedidoRepository;

    @Mock
    ProdutoRepository produtoRepository;

    @InjectMocks
    ItensPedidoServiceImpl service;

    List<ItensPedido> itensPedidoList = new ArrayList<>();

    @Captor
    ArgumentCaptor<Integer> integerArgumentCaptor;

    @Test
    void findAll() {
        //given
        given(itensPedidoRepository.findAll()).willReturn(itensPedidoList);
        itensPedidoList.add(mock(ItensPedido.class));

        //when
        List<ItensPedido> returnedList = service.findAll();

        //then
        then(itensPedidoRepository).should().findAll();
        assertThat(returnedList).isNotNull();
        assertThat(returnedList.size()).isEqualTo(1);
    }

    @Test
    void deleteById() {
        //when
        service.deleteById(1);

        //then
        then(itensPedidoRepository).should().deleteById(1);
        then(itensPedidoRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void addProdutos() {
        //given
        List<Integer> listOfIds = Lists.newArrayList(1, 2, 3);
        given(produtoRepository.findById(anyInt()))
                .willAnswer(i -> Optional.of(Produto.builder()
                                    .id(i.getArgument(0))
                                    .build()));

        given(itensPedidoRepository.saveAll(anyList()))
                .willAnswer(i -> i.getArgument(0));

        //when
        List<ItensPedido> returnedItensPedidos = service.addProdutos(mock(Pedido.class), listOfIds);

        //then
        then(produtoRepository).should(times(listOfIds.size())).findById(anyInt());
        then(itensPedidoRepository).should().saveAll(Mockito.<ItensPedido>anyList());
        assertThat(returnedItensPedidos.size()).isEqualTo(listOfIds.size());
    }

}