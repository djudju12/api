package com.comidaderuadev.api.controller;

import com.comidaderuadev.api.entity.DTO.CriaPedidoDTO;
import com.comidaderuadev.api.entity.DTO.ItemPedidoDTO;
import com.comidaderuadev.api.entity.DTO.PedidoDetalhadoDTO;
import com.comidaderuadev.api.entity.mapper.MapStructMapperPedidos;
import com.comidaderuadev.api.entity.pedido.Pedido;
import com.comidaderuadev.api.entity.pedido.TipoPagamento;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
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

    @Nested
    public class PedidoTests{

        List<Pedido> pedidos;

        Pedido pedido;

        Pedido anotherPedido;

        @Captor
        ArgumentCaptor<Pedido> pedidoArgumentCaptor;

        @Captor
        ArgumentCaptor<Integer> integerArgumentCaptor;

        @BeforeEach
        void setUp() {
            given(pedidoService.findAll()).willReturn(pedidos);

            pedido = Pedido.builder()
                    .id(1)
                    .tipoPagamento(new TipoPagamento("PIX"))
                    .pedidoData(LocalDateTime.now())
                    .build();

            anotherPedido = Pedido.builder()
                    .id(2)
                    .tipoPagamento(new TipoPagamento("DINHEIRO"))
                    .pedidoData(LocalDateTime.now())
                    .build();

            given(map.pedidoToPedidoDetalhadoDTO(pedidoArgumentCaptor.capture(), any(PedidoDetalhadoDTO.class)))
                    .willAnswer(invocationOnMock -> {

                        Pedido capturedPedido = invocationOnMock.getArgument(0);
                        return PedidoDetalhadoDTO.builder()
                                .id(capturedPedido.getId())
                                .tipoPagamento(capturedPedido.getTipoPagamento().getDescricao())
                                .pedidoData(capturedPedido.getPedidoData())
                                .itens(Lists.newArrayList(new ItemPedidoDTO()))
                                .build();
                    });
        }

        @AfterEach
        void tearDown() {
            reset(pedidoService);
        }

        @Test
        void findByIdWithDetails() throws Exception {
            //given
            given(pedidoService.findById(anyInt())).willReturn(pedido);

            //when
            mockMvc.perform(MockMvcRequestBuilders.get("/pedidos/" + pedido.getId())
                        .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.itens", hasSize(1)))
                    .andExpect(jsonPath("$.id", is(pedido.getId())));

            //then
            verify(pedidoService).findById(integerArgumentCaptor.capture());
            assertThat(integerArgumentCaptor.getValue()).isEqualTo(pedido.getId());
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
            mockMvc.perform(MockMvcRequestBuilders.post("/pedidos")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(requestJson))
                    .andExpect(status().isCreated());

            //then
            then(tipoPagamentoService).should().findByDescricao(anyString());
            then(itensPedidoService).should().addProdutos(any(Pedido.class), anyList());

        }

        @Test
        void deletePedido() throws Exception {
            //when
            mockMvc.perform(MockMvcRequestBuilders.delete("/pedidos/" + pedido.getId()))
                    .andExpect(status().isOk());

            //then
            verify(pedidoService).delete(integerArgumentCaptor.capture());
            assertThat(integerArgumentCaptor.getValue()).isEqualTo(pedido.getId());
        }
    }

}