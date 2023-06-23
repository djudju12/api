package com.comidaderuadev.api.controller;

import com.comidaderuadev.api.entity.mapper.MapStructMapperPedidos;
import com.comidaderuadev.api.entity.DTO.CriaPedidoDTO;
import com.comidaderuadev.api.entity.DTO.PedidoDTO;
import com.comidaderuadev.api.entity.DTO.PedidoDetalhadoDTO;
import com.comidaderuadev.api.entity.DTO.TipoPagamentoDTO;
import com.comidaderuadev.api.entity.pedido.Pedido;
import com.comidaderuadev.api.entity.pedido.TipoPagamento;
import com.comidaderuadev.api.service.ItensPedidoService;
import com.comidaderuadev.api.service.PedidoService;
import com.comidaderuadev.api.service.ProdutoService;
import com.comidaderuadev.api.service.TipoPagamentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private PedidoService pedidoService;
    private ItensPedidoService itensPedidoService;
    private TipoPagamentoService tipoPagamentoService;
    private MapStructMapperPedidos map;

    public PedidoController(PedidoService pedidoService,
                            ItensPedidoService itensPedidoService,
                            TipoPagamentoService tipoPagamentoService,
                            MapStructMapperPedidos map) {
        this.pedidoService = pedidoService;
        this.itensPedidoService = itensPedidoService;
        this.tipoPagamentoService = tipoPagamentoService;
        this.map = map;
    }

    @GetMapping
    public List<PedidoDTO> findAll() {
        return pedidoService
                .findAll()
                .stream()
                .map(p -> map.pedidoToPedidoDTO(p))
                .toList();
    }
    @GetMapping("/detalhes")
    public List<PedidoDetalhadoDTO> findAllDetalhado() {
        return pedidoService
                .findAll()
                .stream()
                .map(p -> map.pedidoToPedidoDetalhadoDTO(p,
                        new PedidoDetalhadoDTO()))
                .toList();
    }

    @GetMapping("/tiposPagamentos")
    public List<TipoPagamentoDTO> findAllTiposPagamentos() {
        return tipoPagamentoService
                .findAll()
                .stream()
                .map(tp -> map.tipoPagamentoToTipoPagamentoDTO(tp))
                .toList();
    }

    @GetMapping("/{pedidoId}")
    public PedidoDetalhadoDTO findByIdWithDetails(@PathVariable int pedidoId) {
        Pedido pedido = pedidoService.findById(pedidoId);
        return map.pedidoToPedidoDetalhadoDTO(pedido, new PedidoDetalhadoDTO());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPedido(@Valid @RequestBody CriaPedidoDTO criaPedidoDTO) {
        TipoPagamento tipoPagamento = tipoPagamentoService.findByDescricao(criaPedidoDTO.getTipoPagamento());
        itensPedidoService.addProdutos(new Pedido(tipoPagamento), criaPedidoDTO.getItens());
    }

    @DeleteMapping("/{pedidoId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePedido(@PathVariable int pedidoId) {
        pedidoService.delete(pedidoId);
    }

}
