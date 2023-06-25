package com.comidaderuadev.api.entity.mapper;

import com.comidaderuadev.api.entity.DTO.PedidoDTO;
import com.comidaderuadev.api.entity.DTO.PedidoDetalhadoDTO;
import com.comidaderuadev.api.entity.DTO.ProdutoDTO;
import com.comidaderuadev.api.entity.DTO.TipoPagamentoDTO;
import com.comidaderuadev.api.entity.pedido.ItensPedido;
import com.comidaderuadev.api.entity.pedido.Pedido;
import com.comidaderuadev.api.entity.pedido.TipoPagamento;
import com.comidaderuadev.api.entity.produto.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class MapStructMapperPedidosTest {

    final LocalDateTime DATA_TEST = LocalDateTime.now();
    final String DESC_TIPO_PAGAMENTO_TEST = "PIX";
    final TipoPagamento TIPO_PAGAMENTO_TEST = new TipoPagamento(DESC_TIPO_PAGAMENTO_TEST);

    @Mock
    MapStructMapperProdutos MOCK_MAPPER_PRODUTOS;

    @InjectMocks
    MapStructMapperPedidos MAPPER = Mappers.getMapper(MapStructMapperPedidos.class); ;

    @BeforeEach
    void setUp() {
    }

    @Test
    void pedidoToPedidoDTO() {
        //given
        Pedido pedido = Pedido.builder()
                .id(1)
                .tipoPagamento(TIPO_PAGAMENTO_TEST)
                .pedidoData(DATA_TEST)
                .build();

        //when
        PedidoDTO pedidoDTO = MAPPER.pedidoToPedidoDTO(pedido);

        //then
        assertThat(pedidoDTO).isNotNull();
        assertThat(pedidoDTO.getId()).isEqualTo(pedido.getId());
        assertThat(pedidoDTO.getTipoPagamento()).isEqualTo(pedido.getTipoPagamento().getDescricao());
        assertThat(pedidoDTO.getPedidoData()).isEqualTo(pedido.getPedidoData());
    }

    @Test
    void pedidoToPedidoDetalhadoDTO() {
        //given
        Pedido pedido = Pedido.builder()
                .id(1)
                .tipoPagamento(TIPO_PAGAMENTO_TEST)
                .pedidoData(DATA_TEST)
                .build();

        ItensPedido umItemPedido = ItensPedido.builder()
                .vendaId(1)
                .produto(mock(Produto.class))
                .pedido(pedido)
                .build();

        pedido.addProduto(umItemPedido);
        given(MOCK_MAPPER_PRODUTOS.produtoToProdutoDTO(any(Produto.class))).willReturn(new ProdutoDTO());

        //when
        PedidoDetalhadoDTO pedidoDetalhadoDTO = MAPPER.pedidoToPedidoDetalhadoDTO(pedido,
                new PedidoDetalhadoDTO());

        //then
        assertThat(pedidoDetalhadoDTO).isNotNull();
        assertThat(pedidoDetalhadoDTO.getId()).isEqualTo(pedido.getId());
        assertThat(pedidoDetalhadoDTO.getTipoPagamento()).isEqualTo(pedido.getTipoPagamento().getDescricao());
        assertThat(pedidoDetalhadoDTO.getPedidoData()).isEqualTo(pedido.getPedidoData());
        assertThat(pedidoDetalhadoDTO.getItens().size()).isEqualTo(pedido.getItens().size());

    }

    @Test
    void tipoPagamentoToTipoPagamentoDTO() {
        //when
        TipoPagamentoDTO tipoPagamentoDTO = MAPPER.tipoPagamentoToTipoPagamentoDTO(TIPO_PAGAMENTO_TEST);

        //then
        assertThat(tipoPagamentoDTO).isNotNull();
        assertThat(tipoPagamentoDTO.getDescricao()).isEqualTo(DESC_TIPO_PAGAMENTO_TEST);
    }

}