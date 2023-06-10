package com.comidaderuadev.api.service;

import java.util.List;

import com.comidaderuadev.api.entity.produto.Produto;

public interface ProdutoService {
    public List<Produto> findAll();
    public Produto findById(int id);
    public Produto add(Produto produto);
    public Produto update(Produto produto);
    public void delete(int id);
}
