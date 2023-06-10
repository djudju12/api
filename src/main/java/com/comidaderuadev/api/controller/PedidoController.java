package com.comidaderuadev.api.controller;

import java.util.List;
import java.util.Optional;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comidaderuadev.api.entity.pedido.ItensPedido;
import com.comidaderuadev.api.entity.pedido.Pedido;
import com.comidaderuadev.api.entity.pedido.StatusPedido;
import com.comidaderuadev.api.entity.pedido.TipoPagamento;
import com.comidaderuadev.api.entity.pedido.DTO.ItensPedidoDTO;
import com.comidaderuadev.api.entity.pedido.DTO.PedidoDTO;
import com.comidaderuadev.api.entity.produto.Produto;
import com.comidaderuadev.api.entity.produto.DTO.ProdutoDTO;
import com.comidaderuadev.api.exceptions.produto.NotFoundException;
import com.comidaderuadev.api.repository.ItensPedidoRepository;
import com.comidaderuadev.api.repository.PedidoRepository;
import com.comidaderuadev.api.repository.StatusPedidoRepository;
import com.comidaderuadev.api.repository.TipoPagamentoRepository;
import com.comidaderuadev.api.service.ItensPedidoService;
import com.comidaderuadev.api.service.PedidoService;
import com.comidaderuadev.api.service.ProdutoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ItensPedidoService itensPedidoService;

    @Autowired
    private StatusPedidoRepository statusPedidoRepository;

    @Autowired
    private TipoPagamentoRepository tipoPagamentoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<PedidoDTO> findAll() {
        return pedidoService
                .findAll()
                .stream()
                .map(pedido -> convertToDTO(pedido))
                .toList();
    }

    @GetMapping("/{pedidoId}")
    public PedidoDTO findById(@PathVariable int pedidoId) {
        Pedido p = pedidoService.findById(pedidoId);
        return convertToDTO(p);
    }

    // @GetMapping("/{pedidoId}/itens")
    // public Pedido findByIdWithDetails(@PathVariable int pedidoId){
    // return null;
    // }

    @PostMapping("/{pedidoId}/itens/{produtoId}")
    public PedidoDTO findByIdWithDetails(@PathVariable int pedidoId, @PathVariable int produtoId) {
        Pedido pedido = pedidoService.findById(pedidoId);
        Produto produto = produtoService.findById(produtoId);

        Pedido pedidoCriado = pedidoService.addProduto(pedido, produto);
        return convertToDTO(pedidoCriado);
    }

    // @DeleteMapping("/{pedidoId}/itens/{itensPed}")
    // public ItensPedidoDTO findByIdWithDetails(@PathVariable int pedidoId){
    // return null;
    // }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDTO addPedido(@RequestBody PedidoDTO pedidoDTO) {
        Pedido pedido = convertToEntity(pedidoDTO);
        Pedido pedidoCriado = pedidoService.add(pedido);
        return convertToDTO(pedidoCriado);
    }

    @PutMapping
    public PedidoDTO updatePedido(@RequestBody PedidoDTO pedidoDTO) {
        Pedido pedido = convertToEntity(pedidoDTO);
        Pedido pedidoEditado = pedidoService.update(pedido);
        return convertToDTO(pedidoEditado);
    }

    private PedidoDTO convertToDTO(Pedido pedido) {
        PedidoDTO pedidoDTO = modelMapper.map(pedido, PedidoDTO.class);
        pedidoDTO.setPedidoStatus(pedido.getStatus().getDescricao());
        pedidoDTO.setPedidoTipoPagamento(pedido.getTipoPagamento().getDescricao());
        pedidoDTO.setItens(
            itensPedidoService
                .findByPedido(pedido)
                .stream()
                .map(item -> convertToDTO(item))
                .toList());
        return pedidoDTO;
    }

    private ItensPedidoDTO convertToDTO(ItensPedido item) {
        ItensPedidoDTO itensPedidoDTO = modelMapper.map(item, ItensPedidoDTO.class);
        ProdutoDTO produtoDTO = modelMapper.map(item.getProduto(), ProdutoDTO.class);
        itensPedidoDTO.setProduto(produtoDTO);
        return itensPedidoDTO;
    }

    private Pedido convertToEntity(PedidoDTO pedidoDTO) {
        StatusPedido statusPedido = statusPedidoRepository.findByDescricao(pedidoDTO.getPedidoStatus());
        TipoPagamento tipoPagamento = tipoPagamentoRepository.findByDescricao(pedidoDTO.getPedidoTipoPagamento());

        if (statusPedido == null)
            throw new NotFoundException("Status do pedido não encontrado. Status: " + pedidoDTO.getPedidoStatus());

        if (tipoPagamento == null)
            throw new NotFoundException(
                    "Tipo do pagamento não encontrado. Tipo do pagamento: " + pedidoDTO.getPedidoTipoPagamento());

        Pedido pedido = modelMapper.map(pedidoDTO, Pedido.class);
        pedido.setStatus(statusPedido);
        pedido.setTipoPagamento(tipoPagamento);

        return pedido;
    }
}
