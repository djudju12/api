package com.comidaderuadev.api.controller;

import java.util.List;
import java.util.Optional;

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
import com.comidaderuadev.api.exceptions.produto.NotFoundException;
import com.comidaderuadev.api.repository.PedidoRepository;
import com.comidaderuadev.api.repository.StatusPedidoRepository;
import com.comidaderuadev.api.repository.TipoPagamentoRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private StatusPedidoRepository statusPedidoRepository;

    @Autowired
    private TipoPagamentoRepository tipoPagamentoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<PedidoDTO> findAll() {
        return pedidoRepository
                .findAll()
                .stream()
                .map(pedido -> convertToDTO(pedido))
                .toList();
    }

    @GetMapping("/{pedidoId}")
    public PedidoDTO findById(@PathVariable int pedidoId) {
        Optional<Pedido> pedido = pedidoRepository.findById(pedidoId);

        if (pedido.isPresent())
            return convertToDTO(pedido.get());
    
        throw new NotFoundException("Pedido não encontrado. Id: " + pedidoId);
    }

    // @GetMapping("/{pedidoId}/details")
    // public CarrinhoDTO(?) findByIdWithDetails(@PathVariable int pedidoId){
        // return null;
    // }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDTO addPedido(@RequestBody PedidoDTO pedidoDTO) {
        Pedido pedido = convertToEntity(pedidoDTO);
        pedido.setId(0);
        
        Pedido pedidoCriado = pedidoRepository.save(pedido);
        return convertToDTO(pedidoCriado);
    }

    @PutMapping
    public PedidoDTO updatePedido(@RequestBody PedidoDTO pedidoDTO) {
        if (!pedidoRepository.existsById(pedidoDTO.getPedidoId()))
            throw new NotFoundException("Pedido não encontrado. Id: " + pedidoDTO.getPedidoId());
        
        Pedido pedido = convertToEntity(pedidoDTO);
        Pedido pedidoEditado = pedidoRepository.save(pedido);
        return convertToDTO(pedidoEditado);
    }

    private PedidoDTO convertToDTO(Pedido pedido) {
        PedidoDTO pedidoDTO = modelMapper.map(pedido, PedidoDTO.class);
        pedidoDTO.setPedidoStatus(pedido.getStatus().getDescricao());
        pedidoDTO.setPedidoTipoPagamento(pedido.getTipoPagamento().getDescricao());
        return pedidoDTO;
    }

    private Pedido convertToEntity(PedidoDTO pedidoDTO) {
        StatusPedido statusPedido = statusPedidoRepository.findByDescricao(pedidoDTO.getPedidoStatus());
        TipoPagamento tipoPagamento = tipoPagamentoRepository.findByDescricao(pedidoDTO.getPedidoTipoPagamento());
        
        if (statusPedido == null) 
            throw new NotFoundException("Status do pedido não encontrado. Status: " + pedidoDTO.getPedidoStatus());

        if (tipoPagamento == null)
            throw new NotFoundException("Tipo do pagamento não encontrado. Tipo do pagamento: " + pedidoDTO.getPedidoTipoPagamento());

        Pedido pedido = modelMapper.map(pedidoDTO, Pedido.class);
        pedido.setStatus(statusPedido);
        pedido.setTipoPagamento(tipoPagamento);

        return pedido;
    }
}
