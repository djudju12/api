package com.comidaderuadev.api.service.implementation;

import com.comidaderuadev.api.entity.produto.Categoria;
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

@ExtendWith(MockitoExtension.class)
class CategoriaServiceImplTest {

    @Mock
    CategoriaRepository repository;

    @InjectMocks
    CategoriaServiceImpl service;

    final String DESC = "BRASILEIRA";

    @Test
    void findAll() {
        //given
        List<Categoria> categoriaList = new ArrayList<>();
        given(repository.findAll()).willReturn(categoriaList);

        //when
        categoriaList.add(mock(Categoria.class));
        categoriaList.add(mock(Categoria.class));
        List<Categoria> returnedCategoriaList = service.findAll();

        //then
        then(repository).should().findAll();
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
        Categoria categoria = new Categoria(DESC);
        given(repository.save(categoria)).willReturn(categoria);

        //then
        Categoria addedCategoria = service.add(categoria);

        //when
        assertThat(addedCategoria).isNotNull();
        assertThat(addedCategoria.getId()).isEqualTo(0);
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