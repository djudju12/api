package com.comidaderuadev.api.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comidaderuadev.api.entity.produto.Produto;
import com.comidaderuadev.api.exceptions.produto.NotFoundException;
import com.comidaderuadev.api.repository.ProdutoRepository;
import com.comidaderuadev.api.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    @Override
    public Produto findById(int id) {
        Produto p = produtoRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado. Id: " + id));
        return p;
    }

    @Override
    public Produto add(Produto produto) {
        produto.setId(0);
        return produtoRepository.save(produto);
    }

    @Override
    public Produto update(Produto produto) {
        if (!produtoRepository.existsById(produto.getId()))
            throw new NotFoundException("Produto não encontrado. Produto: " + produto);

        return produtoRepository.save(produto);
    }

    @Override
    public void delete(int id) {
        Produto p = produtoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado. Id: " + id));
        produtoRepository.delete(p);
    }

}
