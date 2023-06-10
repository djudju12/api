package com.comidaderuadev.api.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comidaderuadev.api.entity.produto.Categoria;
import com.comidaderuadev.api.exceptions.produto.NotFoundException;
import com.comidaderuadev.api.repository.CategoriaRepository;
import com.comidaderuadev.api.service.CategoriaService;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria findByDescricao(String categoriaDescricao) {
        Categoria c = categoriaRepository.findByDescricao(categoriaDescricao)
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada. Categoria: " + categoriaDescricao));

        return c;
    }

    @Override
    public Categoria add(Categoria categoria) {
        categoria.setId(0);
        return categoriaRepository.save(categoria);
    }

    @Override
    public void delete(String categoriaDescricao) {
        Categoria c = categoriaRepository.findByDescricao(categoriaDescricao)
                .orElseThrow(() -> new NotFoundException("Categoria não encontrada. Categoria: " + categoriaDescricao));

        categoriaRepository.delete(c);
    }

}
