package com.comidaderuadev.api.entity.mapper;

import com.comidaderuadev.api.entity.DTO.PedidoDTO;
import com.comidaderuadev.api.entity.DTO.PedidoDetalhadoDTO;
import com.comidaderuadev.api.entity.DTO.TipoPagamentoDTO;
import com.comidaderuadev.api.entity.pedido.Pedido;
import com.comidaderuadev.api.entity.pedido.TipoPagamento;
import com.comidaderuadev.api.entity.produto.Categoria;
import com.comidaderuadev.api.service.CategoriaService;
import com.comidaderuadev.api.service.TipoPagamentoService;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        uses = MapStructMapperProdutos.class )
public interface MapStructMapperPedidos {

    MapStructMapperPedidos INSTANCE = Mappers.getMapper(MapStructMapperPedidos.class);

    PedidoDTO pedidoToPedidoDTO(Pedido pedido);

    PedidoDetalhadoDTO pedidoToPedidoDetalhadoDTO(Pedido pedido, @MappingTarget PedidoDetalhadoDTO pedidoDetalhadoDTO);

    TipoPagamentoDTO tipoPagamentoToTipoPagamentoDTO(TipoPagamento tipoPagamento);

    default String mapTipoPagamentoToString(TipoPagamento tipoPagamento) {
        return tipoPagamento.getDescricao();
    }

}
