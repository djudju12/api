package com.comidaderuadev.api.service.implementation;

import com.comidaderuadev.api.entity.produto.Categoria;
import com.comidaderuadev.api.entity.produto.Produto;
import com.comidaderuadev.api.exceptions.NotFoundException;
import com.comidaderuadev.api.repository.CategoriaRepository;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.lang.model.util.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CategoriaServiceImplTest {

    @Mock
    CategoriaRepository repository;

    @InjectMocks
    CategoriaServiceImpl service;

    final String DESC = "BRASILEIRA";

    @Captor
    private ArgumentCaptor<Categoria> captor;

    @Test
    void findAll() {
        //given
        List<Categoria> categoriaList = new ArrayList<>();
        given(repository.findAll()).willReturn(categoriaList);
        categoriaList.add(mock(Categoria.class));
        categoriaList.add(mock(Categoria.class));

        //when
        List<Categoria> returnedCategoriaList = service.findAll();

        //then
        then(repository).should().findAll();
        assertThat(returnedCategoriaList).isNotNull();
        assertThat(returnedCategoriaList.size()).isEqualTo(2);
    }

    @Test
    void findByDescricao() {
        //given
        given(repository.findByDescricao(DESC)).willReturn(Optional.of(new Categoria(DESC)));

        //when
        Categoria returnedCategoria = service.findByDescricao(DESC);

        //then
        then(repository).should().findByDescricao(DESC);
        assertThat(returnedCategoria).isNotNull();
        assertThat(returnedCategoria.getDescricao()).isEqualTo(DESC);
    }

    @Test
    void add() {
        //given
        Categoria expectedCategoria = new Categoria();
        expectedCategoria.setId(1);
        given(repository.save(any(Categoria.class))).willReturn(expectedCategoria);

        //then
        Categoria savedCategoria = new Categoria();
        savedCategoria.setId(5);
        Categoria returnedCategoria = service.add(savedCategoria);

        //when
        verify(repository).save(captor.capture());
        Categoria capturedCategoria = captor.getValue();
        assertThat(capturedCategoria.getId()).isEqualTo(0);
        assertThat(returnedCategoria.getId()).isEqualTo(1);
    }

    @Test
    void deleteSuccess() {
        //given
        given(repository.findByDescricao(DESC)).willReturn(Optional.of(new Categoria(DESC)));

        //when
        service.delete(DESC);

        //then
        then(repository).should()
                .delete(argThat(arg -> arg.getDescricao().equals(DESC)));
    }
    @Test
    void deleteFail() {
        //given
        final String NOT_FOUND = "THROWERROR";
        given(repository.findByDescricao(anyString())).willReturn(Optional.empty());

        //when / then
        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> service.delete(NOT_FOUND));
    }
}