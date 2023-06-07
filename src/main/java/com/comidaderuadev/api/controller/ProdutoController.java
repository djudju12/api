package com.comidaderuadev.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comidaderuadev.api.entity.Produto;
import com.comidaderuadev.api.repository.ProdutoRepository;

@RestController
public class ProdutoController {
    
    private ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoController(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }
    
    @GetMapping("/produtos")
    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }
}
