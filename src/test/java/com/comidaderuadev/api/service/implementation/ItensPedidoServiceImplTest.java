package com.comidaderuadev.api.service.implementation;

import com.comidaderuadev.api.entity.pedido.ItensPedido;
import com.comidaderuadev.api.entity.produto.Produto;
import com.comidaderuadev.api.repository.ItensPedidoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

    @Test
    void addProduto() {
        //given
        Produto produto = new Produto();
        ItensPedido savedItensPedido = new ItensPedido(produto);
        given(repository.save(any(ItensPedido.class))).willReturn(savedItensPedido);

        //when
        ItensPedido itensPedido = service.addProduto(produto);

        //then
        then(repository).should().save(any(ItensPedido.class));
        then(repository).shouldHaveNoMoreInteractions();
        assertThat(itensPedido).isNotNull();
        assertThat(itensPedido).isEqualTo(savedItensPedido);
    }

    @Test
    void addProdutos() {
        //given
        Produto produto = new Produto();
        int quantidadeProduto = 10;
        List<ItensPedido> expectedItens = new ArrayList<>();
        for (int i = 0; i < quantidadeProduto; i++) {
            ItensPedido item = new ItensPedido(produto);
            expectedItens.add(item);
        }
        given(repository.saveAll(ArgumentMatchers.<ItensPedido>anyList())).willReturn(expectedItens);

        //when
        List<ItensPedido> result = service.addProdutos(produto, quantidadeProduto);

        //then
        verify(repository).saveAll(captor.capture());
        List<ItensPedido> capturedItens = captor.getValue();
        assertEquals(quantidadeProduto, capturedItens.size());
        assertEquals(expectedItens, result);
    }
}