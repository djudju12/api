package com.comidaderuadev.api.controller;

import com.comidaderuadev.api.entity.DTO.PedidoDetalhadoDTO;
import com.comidaderuadev.api.entity.mapper.MapStructMapperPedidos;
import com.comidaderuadev.api.entity.pedido.Pedido;
import com.comidaderuadev.api.entity.pedido.TipoPagamento;
import com.comidaderuadev.api.service.ItensPedidoService;
import com.comidaderuadev.api.service.PedidoService;
import com.comidaderuadev.api.service.ProdutoService;
import com.comidaderuadev.api.service.TipoPagamentoService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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

            given(map.pedidoToPedidoDetalhadoDTO(any(Pedido.class), any(PedidoDetalhadoDTO.class)))
                    .willReturn();

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
                    .andDo(print());
        }

        @Test
        void createPedido() {
        }

        @Test
        void deletePedido() {
        }
    }

}