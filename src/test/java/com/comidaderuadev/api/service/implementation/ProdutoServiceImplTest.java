package com.comidaderuadev.api.service.implementation;

import com.comidaderuadev.api.entity.produto.Produto;
import com.comidaderuadev.api.exceptions.NotFoundException;
import com.comidaderuadev.api.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.lang.model.util.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceImplTest {

    @Mock
    ProdutoRepository repository;

    @InjectMocks
    ProdutoServiceImpl service;

    List<Produto> produtoList = new ArrayList<>();

    @Captor
    ArgumentCaptor<Produto> captor;

    Produto expectedProduto;


    @BeforeEach
    void setUp() {
        expectedProduto = new Produto();
    }

    @Test
    void findAll() {
        //given
        produtoList.add(mock(Produto.class));
        given(repository.findAll()).willReturn(produtoList);

        //when
        List<Produto> returnedProdutoList = service.findAll();

        //then
        then(repository).should(times(1)).findAll();
        then(repository).shouldHaveNoMoreInteractions();
        assertThat(returnedProdutoList.size()).isEqualTo(1);
    }

    @Test
    void findByIdSuccess() {
        //given
        given(repository.findById(anyInt())).willReturn(Optional.of(expectedProduto));

        //when
        Produto returnedProduto = service.findById(1);

        //then
        then(repository).should(times(1)).findById(1);
        then(repository).shouldHaveNoMoreInteractions();
        assertThat(returnedProduto).isEqualTo(expectedProduto);
    }
    @Test
    void findByIdThrows() {
        //given
        given(repository.findById(anyInt())).willReturn(Optional.empty());

        //then
        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> service.findById(1));
        then(repository).shouldHaveNoMoreInteractions();
    }

    @Test
    void add() {
        //given
        expectedProduto.setId(1);
        given(repository.save(any(Produto.class))).willReturn(expectedProduto);

        //when
        Produto savedProduto = new Produto();
        savedProduto.setId(5);
        Produto returnedProduto = service.add(savedProduto);

        //then
        verify(repository).save(captor.capture());
        then(repository).shouldHaveNoMoreInteractions();
        Produto capturedProduto = captor.getValue();
        assertThat(capturedProduto.getId()).isEqualTo(0);
        assertThat(returnedProduto.getId()).isEqualTo(1);
    }

    @Test
    void updateSuccess() {
        //given
        expectedProduto.setId(5);
        given(repository.existsById(anyInt())).willReturn(true);
        given(repository.save(any(Produto.class))).willReturn(expectedProduto);
        ArgumentCaptor<Integer> captorInt = ArgumentCaptor.forClass(Integer.class);

        //when
        Produto returnedProduto = service.update(expectedProduto);

        //then
        then(repository).should(times(1)).existsById(captorInt.capture());
        then(repository).should(times(1)).save(expectedProduto);
        then(repository).shouldHaveNoMoreInteractions();
        assertThat(returnedProduto).isEqualTo(expectedProduto);
        assertThat(captorInt.getValue()).isEqualTo(expectedProduto.getId());
    }

    @Test
    void updateThrows() {
        //given
        given(repository.existsById(anyInt())).willReturn(false);

        //when/then
        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> service.update(new Produto()));
        then(repository).shouldHaveNoMoreInteractions();
    }

    @Test
    void deleteSuccess() {
        //given
        given(repository.findById(anyInt())).willReturn(Optional.of(expectedProduto));

        //when
        service.delete(1);

        //then
        then(repository).should(times(1)).findById(1);
        then(repository).should(times(1)).delete(expectedProduto);
        then(repository).shouldHaveNoMoreInteractions();
    }
    @Test
    void deleteThrows() {
        //given
        given(repository.findById(anyInt())).willReturn(Optional.empty());

        //when / then
        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(
                        () -> service.delete(1)
                );
        then(repository).shouldHaveNoMoreInteractions();
    }
}