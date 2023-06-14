package com.comidaderuadev.api.controller;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.comidaderuadev.api.entity.pedido.ItensPedido;
import com.comidaderuadev.api.entity.pedido.DTO.VendaDTO;
import com.comidaderuadev.api.entity.produto.Produto;
import com.comidaderuadev.api.service.ItensPedidoService;
import com.comidaderuadev.api.service.ProdutoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private ItensPedidoService itensPedidoService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<VendaDTO> findAllWithDetail() {
        return itensPedidoService
                .findAll()
                .stream()
                .map(item -> convertToDTO(item))
                .toList();
    }


    @PostMapping("/{produtoId}")
    public List<VendaDTO> addVenda(@PathVariable int produtoId, @RequestParam(required = false, defaultValue = "1") int quantidadeProduto) {
        Produto produto = produtoService.findById(produtoId);
        List<ItensPedido> itensPedido = itensPedidoService.addProdutos(produto, quantidadeProduto);
        return itensPedido
                .stream()
                .map(item -> convertToDTO(item))
                .toList();
    }

    @DeleteMapping("/{vendaId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduto(@PathVariable int vendaId) {
        itensPedidoService.deleteById(vendaId);
    }

    private VendaDTO convertToDTO(ItensPedido itensPedido) {
        return modelMapper.map(itensPedido, VendaDTO.class);
    }

}
