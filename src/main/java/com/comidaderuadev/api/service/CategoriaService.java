package com.comidaderuadev.api.service;

import java.util.List;

import com.comidaderuadev.api.entity.produto.Categoria;

public interface CategoriaService {
    public List<Categoria> findAll();
    public Categoria findByDescricao(String descricao);
    public Categoria add(Categoria categoria);
    public void delete(String descricao);
}
