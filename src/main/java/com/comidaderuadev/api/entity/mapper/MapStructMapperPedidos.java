package com.comidaderuadev.api.entity.mapper;

import com.comidaderuadev.api.entity.DTO.PedidoDTO;
import com.comidaderuadev.api.entity.DTO.PedidoDetalhadoDTO;
import com.comidaderuadev.api.entity.DTO.TipoPagamentoDTO;
import com.comidaderuadev.api.entity.pedido.Pedido;
import com.comidaderuadev.api.entity.pedido.TipoPagamento;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        uses = MapStructMapperProdutos.class )
public interface MapStructMapperPedidos {

    MapStructMapperPedidos INSTANCE = Mappers.getMapper(MapStructMapperPedidos.class);

    PedidoDTO pedidoToPedidoDTO(Pedido pedido);

    Pedido pedidoDTO_ToPedido(Pedido pedido);

    PedidoDetalhadoDTO pedidoToPedidoDetalhadoDTO(Pedido pedido, @MappingTarget PedidoDetalhadoDTO pedidoDetalhadoDTO);

    TipoPagamentoDTO tipoPagamentoToTipoPagamentoDTO(TipoPagamento tipoPagamento);

    default String mapTipoPagamentoToString(TipoPagamento tipoPagamento) {
        return tipoPagamento.getDescricao();
    }

}
