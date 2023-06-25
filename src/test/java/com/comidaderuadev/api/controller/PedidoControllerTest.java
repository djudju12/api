package com.comidaderuadev.api.controller;

import com.comidaderuadev.api.entity.DTO.*;
import com.comidaderuadev.api.entity.mapper.MapStructMapperPedidos;
import com.comidaderuadev.api.entity.pedido.Pedido;
import com.comidaderuadev.api.entity.pedido.TipoPagamento;
import com.comidaderuadev.api.exceptions.NotFoundException;
import com.comidaderuadev.api.service.ItensPedidoService;
import com.comidaderuadev.api.service.PedidoService;
import com.comidaderuadev.api.service.TipoPagamentoService;
import com.comidaderuadev.api.utils.JsonWriter;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PedidoController.class)
class PedidoControllerTest {

    @MockBean
    private PedidoService pedidoService;

    @MockBean
    private ItensPedidoService itensPedidoService;

    @MockBean
    private TipoPagamentoService tipoPagamentoService;

    @MockBean
    private MapStructMapperPedidos map;

    @Autowired
    private MockMvc mockMvc;

    @AfterEach
    void tearDown() {
        reset(pedidoService);
        reset(itensPedidoService);
        reset(tipoPagamentoService);
    }

    @Nested
    public class PedidoTests {

        List<Pedido> pedidos;

        Pedido pedido;

        @Captor
        ArgumentCaptor<Integer> integerArgumentCaptor;

        @BeforeEach
        void setUp() {
            pedidos = new ArrayList<>();
            pedido = Pedido.builder()
                    .id(1)
                    .tipoPagamento(new TipoPagamento("PIX"))
                    .pedidoData(LocalDateTime.now())
                    .build();

            pedidos.add(pedido);

            given(pedidoService.findAll()).willReturn(pedidos);

            given(map.pedidoToPedidoDetalhadoDTO(any(Pedido.class), any(PedidoDetalhadoDTO.class)))
                    .willAnswer(invocationOnMock -> {

                        Pedido capturedPedido = invocationOnMock.getArgument(0);
                        return PedidoDetalhadoDTO.builder()
                                .id(capturedPedido.getId())
                                .tipoPagamento(capturedPedido.getTipoPagamento().getDescricao())
                                .pedidoData(capturedPedido.getPedidoData())
                                .itens(Lists.newArrayList(new ItemPedidoDTO()))
                                .build();
                    });

            given(map.pedidoToPedidoDTO(any(Pedido.class)))
                    .willAnswer(invocationOnMock -> {

                        Pedido capturedPedido = invocationOnMock.getArgument(0);
                        return PedidoDTO.builder()
                                .id(capturedPedido.getId())
                                .tipoPagamento(capturedPedido.getTipoPagamento().getDescricao())
                                .pedidoData(capturedPedido.getPedidoData())
                                .build();
                    });
        }

        @Test
        void findAll() throws Exception {
            //when
            mockMvc.perform(get("/pedidos")
                        .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(pedidos.size())))
                    .andExpect(jsonPath("$.[0].idPedido", is(pedido.getId())))
                    .andExpect(jsonPath("$.[0].tipoPagamento", is(pedido.getTipoPagamento().getDescricao())));

            //then
            then(pedidoService).should().findAll();
            then(pedidoService).shouldHaveNoMoreInteractions();
        }

        @Test
        void findAllWithDetail() throws Exception {
            //when
            mockMvc.perform(get("/pedidos/detalhes")
                        .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(pedidos.size())))
                    .andExpect(jsonPath("$.[0].idPedido", is(pedido.getId())))
                    .andExpect(jsonPath("$.[0].tipoPagamento", is(pedido.getTipoPagamento().getDescricao())))
                    .andExpect(jsonPath("$.[0].itens", hasSize(1)));

            //then
            then(pedidoService).should().findAll();
            then(pedidoService).shouldHaveNoMoreInteractions();
        }

        @Test
        void findByIdWithDetails() throws Exception {
            //given
            given(pedidoService.findById(anyInt())).willReturn(pedido);

            //when
            mockMvc.perform(get("/pedidos/" + pedido.getId())
                        .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.itens", hasSize(1)))
                    .andExpect(jsonPath("$.idPedido", is(pedido.getId())));

            //then
            verify(pedidoService).findById(integerArgumentCaptor.capture());
            assertThat(integerArgumentCaptor.getValue()).isEqualTo(pedido.getId());
        }

        @Test
        void findByIdWithDetails_ThrowsNotFound() throws Exception {
            //given
            int NOT_FOUND = 404;
            given(pedidoService.findById(anyInt())).willThrow(
                    new NotFoundException("Pedido nao encontrado. Id:" + NOT_FOUND));

            //when
            mockMvc.perform(get("/pedidos/" + NOT_FOUND)
                        .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.status").exists())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.timeStamp").exists());

        }

        @Test
        void createPedido() throws Exception {
            //given
            String requestJson = JsonWriter.toJsonString(
                    new CriaPedidoDTO(Lists.newArrayList(1, 2, 3)
                            , "PIX")
            );

            given(tipoPagamentoService.findByDescricao(anyString())).willReturn(new TipoPagamento("PIX"));

            //when
            mockMvc.perform(post("/pedidos")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestJson))
                    .andExpect(status().isCreated());

            //then
            then(tipoPagamentoService).should().findByDescricao(anyString());
            then(itensPedidoService).should().addProdutos(any(Pedido.class), anyList());

        }

        @Test
        void createPedidoWithInvalidDTO() throws Exception {
            //given
            String invalidContent = "{\"invalidJson\": true}";

            //when
            mockMvc.perform(post("/pedidos")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(invalidContent))
                    .andExpect(status().is4xxClientError())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.status").exists())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.message").exists())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.timeStamp").exists());


        }

        @Test
        void deletePedido() throws Exception {
            //when
            mockMvc.perform(delete("/pedidos/" + pedido.getId()))
                    .andExpect(status().isOk());

            //then
            verify(pedidoService).delete(integerArgumentCaptor.capture());
            assertThat(integerArgumentCaptor.getValue()).isEqualTo(pedido.getId());
        }
    }

    @Nested
    public class TipoPagamentoTests {

        List<TipoPagamento> tipoPagamentos;

        TipoPagamento tipoPagamento;

        @BeforeEach
        void setUp() {
            tipoPagamentos = new ArrayList<>();
            tipoPagamento = new TipoPagamento("PIX");
            tipoPagamentos.add(tipoPagamento);

            given(map.tipoPagamentoToTipoPagamentoDTO(any(TipoPagamento.class)))
                    .willAnswer(invocationOnMock -> {
                        TipoPagamento tipoPagamento = invocationOnMock.getArgument(0);
                        return new TipoPagamentoDTO(tipoPagamento.getDescricao());
                    });
        }

        @Test
        void findAll() throws Exception {
            //given
            given(tipoPagamentoService.findAll()).willReturn(tipoPagamentos);

            //when
            mockMvc.perform(get("/pedidos/tiposPagamentos")
                        .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(1)))
                    .andExpect(jsonPath("$.[0].descricao", is(tipoPagamento.getDescricao())));

            //then
            then(tipoPagamentoService).should().findAll();
            then(tipoPagamentoService).shouldHaveNoMoreInteractions();
        }
    }
}