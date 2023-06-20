package com.comidaderuadev.api.controller;

import com.comidaderuadev.api.entity.produto.Categoria;
import com.comidaderuadev.api.entity.produto.DTO.ProdutoDTO;
import com.comidaderuadev.api.entity.produto.Produto;
import com.comidaderuadev.api.service.CategoriaService;
import com.comidaderuadev.api.service.ProdutoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.then;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.core.Is.is;

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

    @Nested
    public class ProdutoTests {
        private final String DESC = "ALEMA";
        ProdutoDTO validProdutoDTO;

        ProdutoDTO anotherValidProdutoDTO;

        List<Produto> produtos = new ArrayList<>();

        Produto produto;

        Produto anotherProduto;

        @Captor
        ArgumentCaptor<Produto> captor;

        @Captor
        ArgumentCaptor<ProdutoDTO> produtoDTOArgumentCaptor;

        @Captor
        ArgumentCaptor<String> stringArgumentCaptor;

        @BeforeEach
        void setUp() {
            validProdutoDTO = ProdutoDTO.builder()
                    .produtoId(1)
                    .produtoDescricao("PAO")
                    .categoria(DESC)
                    .produtoValor(15.0)
                    .build();

            anotherValidProdutoDTO = ProdutoDTO.builder()
                    .produtoId(2)
                    .produtoDescricao("TORTA")
                    .categoria(DESC)
                    .produtoValor(15.0)
                    .build();

            produto = Produto.builder()
                    .id(1)
                    .produtoDescricao("PAO")
                    .categoria(new Categoria(DESC))
                    .produtoValor(15.0)
                    .build();

            anotherProduto = Produto.builder()
                    .id(2)
                    .produtoDescricao("TORTA")
                    .categoria(new Categoria(DESC))
                    .produtoValor(15.0)
                    .build();

            produtos.add(produto);
            produtos.add(anotherProduto);

            // Converte de entidade para DTO
            given(modelMapper.map(captor.capture(), Mockito.eq(ProdutoDTO.class)))
                    .willAnswer( invocationOnMock -> {
                        Produto capturedProduto = invocationOnMock.getArgument(0);
                        switch (capturedProduto.getId()) {
                            case 1 -> {
                                return validProdutoDTO;
                            }
                            case 2 -> {
                                return anotherValidProdutoDTO;
                            }
                            default -> throw new RuntimeException("Invalid argument");
                        }
                    });

            // Converte de DTO para entidade
            given(modelMapper.map(produtoDTOArgumentCaptor.capture(), Mockito.eq(Produto.class)))
                    .willAnswer( invocationOnMock -> {
                        ProdutoDTO capturedProduto = invocationOnMock.getArgument(0);
                        System.out.println(capturedProduto);
                        switch (capturedProduto.getProdutoId()) {
                            case 1 -> {
                                return produto;
                            }
                            case 2 -> {
                                return anotherProduto;
                            }
                            default -> throw new RuntimeException("Invalid argument");
                        }
                    });

            given(categoriaService.findByDescricao(anyString())).willReturn(new Categoria(DESC));
        }

        @AfterEach
        void tearDown() {
            Mockito.reset(produtoService);
        }

        @Test
        void findAll() throws Exception {
            //given
            given(produtoService.findAll()).willReturn(produtos);

            //when
            mockMvc.perform(MockMvcRequestBuilders.get("/produtos")
                            .accept(MediaType.APPLICATION_JSON))
                    //then
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$", hasSize(2)))
                    .andExpect(jsonPath("$.[0].produtoId", is(validProdutoDTO.getProdutoId())))
                    .andExpect(jsonPath("$.[0].produtoDescricao", is(validProdutoDTO.getProdutoDescricao())))
                    .andExpect(jsonPath("$.[0].produtoValor", is(validProdutoDTO.getProdutoValor())))
                    .andExpect(jsonPath("$.[0].categoria", is(validProdutoDTO.getCategoria())))
                    .andExpect(jsonPath("$.[1].produtoId", is(anotherValidProdutoDTO.getProdutoId())))
                    .andExpect(jsonPath("$.[1].produtoDescricao", is(anotherValidProdutoDTO.getProdutoDescricao())))
                    .andExpect(jsonPath("$.[1].produtoValor", is(anotherValidProdutoDTO.getProdutoValor())))
                    .andExpect(jsonPath("$.[1].categoria", is(anotherValidProdutoDTO.getCategoria())));

            then(produtoService).should().findAll();
            then(produtoService).shouldHaveNoMoreInteractions();
        }

        @Test
        void findById() throws Exception {
            //given
            given(produtoService.findById(anyInt())).willReturn(produto);

            //when
            mockMvc.perform(get("/produtos/" + produto.getId())
                            .accept(MediaType.APPLICATION_JSON))
                    //then
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.produtoId", is(validProdutoDTO.getProdutoId())))
                    .andExpect(jsonPath("$.produtoDescricao", is(validProdutoDTO.getProdutoDescricao())))
                    .andExpect(jsonPath("$.produtoValor", is(validProdutoDTO.getProdutoValor())))
                    .andExpect(jsonPath("$.categoria", is(validProdutoDTO.getCategoria())));

            then(produtoService).should().findById(anyInt());
            then(produtoService).shouldHaveNoMoreInteractions();

        }

        @Test
        void addProduto() throws Exception {
            //given
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
            ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
            String requestJson = ow.writeValueAsString(validProdutoDTO);
            given(produtoService.add(any(Produto.class))).willReturn(produto);

            //when
            mockMvc.perform(post("/produtos")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestJson))
                    //then
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.produtoId", is(validProdutoDTO.getProdutoId())))
                    .andExpect(jsonPath("$.produtoDescricao", is(validProdutoDTO.getProdutoDescricao())))
                    .andExpect(jsonPath("$.produtoValor", is(validProdutoDTO.getProdutoValor())))
                    .andExpect(jsonPath("$.categoria", is(validProdutoDTO.getCategoria())));

            then(produtoService).should().add(any(Produto.class));
            then(produtoService).shouldHaveNoMoreInteractions();
        }

        @Test
        void deleteProduto() throws Exception {
            //when
            mockMvc.perform(delete("/produtos/" + anyInt()))
                    .andExpect(status().isOk());

            //then
            then(produtoService).should().delete(anyInt());
            then(produtoService).shouldHaveNoMoreInteractions();
        }
    }

    @Nested
    public class CategoriaTests {

    }

}