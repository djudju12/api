package com.comidaderuadev.api.controller;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comidaderuadev.api.entity.pedido.Pedido;
import com.comidaderuadev.api.entity.pedido.StatusPedido;
import com.comidaderuadev.api.entity.pedido.TipoPagamento;
import com.comidaderuadev.api.entity.pedido.DTO.PedidoDTO;
import com.comidaderuadev.api.entity.pedido.DTO.PedidoDetalhadoDTO;
import com.comidaderuadev.api.entity.pedido.DTO.StatusPedidoDTO;
import com.comidaderuadev.api.entity.pedido.DTO.TipoPagamentoDTO;
import com.comidaderuadev.api.entity.produto.Produto;
import com.comidaderuadev.api.exceptions.produto.NotFoundException;
import com.comidaderuadev.api.service.ItensPedidoService;
import com.comidaderuadev.api.service.PedidoService;
import com.comidaderuadev.api.service.ProdutoService;
import com.comidaderuadev.api.service.StatusPedidoService;
import com.comidaderuadev.api.service.TipoPagamentoService;

import org.springframework.web.bind.annotation.DeleteMapping;
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
    private StatusPedidoService statusPedidoService;

    @Autowired
    private TipoPagamentoService tipoPagamentoService;

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

    @GetMapping("/itens")
    public List<PedidoDetalhadoDTO> findAllWithDetail() {
        return pedidoService
                .findAll()
                .stream()
                .map(pedido -> convertToDetailedDTO(pedido))
                .toList();
    }

    @GetMapping("/status")
    public List<StatusPedidoDTO> findAllStatus() {
        return statusPedidoService
                .findAll()
                .stream()
                .map((status) -> convertToDto(status))
                .collect(Collectors.toList());
    }

    @GetMapping("/tiposPagamentos")
    public List<TipoPagamentoDTO> findAllTiposPagamentos() {
        return tipoPagamentoService
                .findAll()
                .stream()
                .map(tipoPagamento -> convertToDTO(tipoPagamento))
                .toList();
    }

    @GetMapping("/{pedidoId}")
    public PedidoDTO findById(@PathVariable int pedidoId) {
        Pedido p = pedidoService.findById(pedidoId);
        return convertToDTO(p);
    }

    @GetMapping("/{pedidoId}/itens")
    public PedidoDetalhadoDTO findByIdWithDetails(@PathVariable int pedidoId) {
        Pedido pedido = pedidoService.findById(pedidoId);
        PedidoDetalhadoDTO pedidoDTO = convertToDetailedDTO(pedido);
        return pedidoDTO;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDTO addPedido(@RequestBody PedidoDTO pedidoDTO) {
        Pedido pedido = convertToEntity(pedidoDTO);
        Pedido pedidoCriado = pedidoService.add(pedido);
        return convertToDTO(pedidoCriado);
    }

    @PostMapping("/{pedidoId}/itens/{produtoId}")
    public PedidoDetalhadoDTO findByIdWithDetails(@PathVariable int pedidoId, @PathVariable int produtoId) {
        Pedido pedido = pedidoService.findById(pedidoId);
        Produto produto = produtoService.findById(produtoId);

        Pedido pedidoCriado = pedidoService.addProduto(pedido, produto);
        return convertToDetailedDTO(pedidoCriado);
    }

    @PostMapping("/status")
    @ResponseStatus(HttpStatus.CREATED)
    public StatusPedidoDTO addStatus(@RequestBody StatusPedidoDTO statusPedidoDTO) throws ParseException {
        StatusPedido statusPedido = convertToEntity(statusPedidoDTO);
        return convertToDto(statusPedidoService.add(statusPedido));
    }

    @PostMapping("/tiposPagamentos")
    @ResponseStatus(HttpStatus.CREATED)
    public TipoPagamentoDTO addTipoPagamento(@RequestBody TipoPagamentoDTO tipoPagamentoDTO) {
        TipoPagamento tipoPagamento = convertToEntity(tipoPagamentoDTO);
        return convertToDTO(tipoPagamentoService.add(tipoPagamento));
    }

    @DeleteMapping("/{pedidoId}/itens/{produtoId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduto(@PathVariable int pedidoId, @PathVariable int produtoId) {
        itensPedidoService.removeProdutoCarrinho(pedidoId, produtoId);
    }

    @DeleteMapping("/status/{descricao}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteStatus(@PathVariable String descricao) {
        StatusPedido statusPedido = statusPedidoService.findByDescricao(descricao);
        if (statusPedido == null)
            throw new NotFoundException("Produto não encontrado. Descricao: " + descricao);

        statusPedidoService.delete(statusPedido);
    }

    @DeleteMapping("/tiposPagamentos/{descricao}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTipoPagamento(@PathVariable String descricao) {
        TipoPagamento tipoPagamento = tipoPagamentoService.findByDescricao(descricao);
        tipoPagamentoService.delete(tipoPagamento);
    }

    @PutMapping("/{pedidoId}")
    public PedidoDTO updatePedido(@RequestBody PedidoDTO pedidoDTO, @PathVariable int pedidoId) {
        pedidoDTO.setPedidoId(pedidoId);
        Pedido pedido = convertToEntity(pedidoDTO);
        Pedido pedidoEditado = pedidoService.update(pedido);
        return convertToDTO(pedidoEditado);
    }

    private PedidoDTO convertToDTO(Pedido pedido) {
        PedidoDTO pedidoDTO = modelMapper.map(pedido, PedidoDTO.class);
        pedidoDTO.setPedidoStatus(pedido.getStatus().getDescricao());
        pedidoDTO.setPedidoTipoPagamento(pedido.getTipoPagamento().getDescricao());
        return pedidoDTO;
    }

    private PedidoDetalhadoDTO convertToDetailedDTO(Pedido pedido) {
        PedidoDetalhadoDTO pedidoDTO = modelMapper.map(pedido, PedidoDetalhadoDTO.class);
        pedidoDTO.setPedidoStatus(pedido.getStatus().getDescricao());
        pedidoDTO.setPedidoTipoPagamento(pedido.getTipoPagamento().getDescricao());
        return pedidoDTO;
    }

    private Pedido convertToEntity(PedidoDTO pedidoDTO) {
        StatusPedido statusPedido = statusPedidoService.findByDescricao(pedidoDTO.getPedidoStatus());
        TipoPagamento tipoPagamento = tipoPagamentoService.findByDescricao(pedidoDTO.getPedidoTipoPagamento());

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

    private StatusPedidoDTO convertToDto(StatusPedido statusPedido) {
        return modelMapper.map(statusPedido, StatusPedidoDTO.class);
    }

    private StatusPedido convertToEntity(StatusPedidoDTO statusPedidoDTO) throws ParseException {
        return modelMapper.map(statusPedidoDTO, StatusPedido.class);
    }

    private TipoPagamentoDTO convertToDTO(TipoPagamento tipoPagamento) {
        return modelMapper.map(tipoPagamento, TipoPagamentoDTO.class);
    }

    private TipoPagamento convertToEntity(TipoPagamentoDTO tipoPagamentoDTO) {
        return modelMapper.map(tipoPagamentoDTO, TipoPagamento.class);
    }

}
