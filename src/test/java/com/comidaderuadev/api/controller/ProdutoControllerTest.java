package com.comidaderuadev.api.controller;

import com.comidaderuadev.api.entity.produto.DTO.ProdutoDTO;
import com.comidaderuadev.api.entity.produto.Produto;
import com.comidaderuadev.api.service.CategoriaService;
import com.comidaderuadev.api.service.ProdutoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProdutoController.class)
class ProdutoControllerTest {

    @MockBean
    ProdutoService produtoService;

    @MockBean
    CategoriaService categoriaService;

    @MockBean
    ModelMapper modelMapper;

    @Autowired
    MockMvc mockMvc;

    ProdutoDTO produtoDTO;

    @BeforeEach
    void setUp() {
        produtoDTO = ProdutoDTO.builder()
                .produtoId(1)
                .produtoDescricao("PAO")
                .categoria("ARABE")
                .produtoValor(15.0)
                .build();
    }

    @AfterEach
    void tearDown() {
        Mockito.reset(produtoService);
    }

    @Test
    void findAll() throws Exception {
        List<Produto> list = new ArrayList<>();
        list.add(new Produto());
        given(produtoService.findAll()).willReturn(list);
        given(modelMapper.map(Mockito.any(Produto.class), Mockito.eq(ProdutoDTO.class))).willReturn(produtoDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/produtos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}