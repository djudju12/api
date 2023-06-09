package com.comidaderuadev.api.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.comidaderuadev.api.entity.pedido.TipoPagamento;
import com.comidaderuadev.api.entity.pedido.DTO.TipoPagamentoDTO;
import com.comidaderuadev.api.exceptions.produto.NotFoundException;
import com.comidaderuadev.api.repository.TipoPagamentoRepository;

@RestController
@RequestMapping("/tiposPagamentos")
public class TipoPagamentoController {
    
    @Autowired
    private TipoPagamentoRepository tipoPagamentoRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<TipoPagamentoDTO> findAll(){
        return tipoPagamentoRepository
                .findAll()
                .stream()
                .map(tipoPagamento -> convertToDTO(tipoPagamento))
                .toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TipoPagamentoDTO addTipoPagamento(@RequestBody TipoPagamentoDTO tipoPagamentoDTO){
        TipoPagamento tipoPagamento = convertToEntity(tipoPagamentoDTO);
        tipoPagamento.setId(0);
        return convertToDTO(tipoPagamentoRepository.save(tipoPagamento));
    }

    @DeleteMapping("/{descricao}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTipoPagamento(@PathVariable String descricao){
        TipoPagamento tipoPagamento = tipoPagamentoRepository.findByDescricao(descricao);
        if (tipoPagamento == null) 
            throw new NotFoundException("Tipo de pagamento não encontrado: " + descricao);

        tipoPagamentoRepository.delete(tipoPagamento);
    
    }

    private TipoPagamentoDTO convertToDTO(TipoPagamento tipoPagamento){
        return modelMapper.map(tipoPagamento, TipoPagamentoDTO.class);
    }
    
    private TipoPagamento convertToEntity(TipoPagamentoDTO tipoPagamentoDTO){
        TipoPagamento tipoPagamento = tipoPagamentoRepository.findByDescricao(tipoPagamentoDTO.getDescricao());

        if (tipoPagamento == null) 
            return modelMapper.map(tipoPagamentoDTO, TipoPagamento.class);
        
        return tipoPagamento;
    }

}
