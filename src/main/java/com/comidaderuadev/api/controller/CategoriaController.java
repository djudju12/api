package com.comidaderuadev.api.controller;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

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

import com.comidaderuadev.api.entity.produto.Categoria;
import com.comidaderuadev.api.entity.produto.DTO.CategoriaDTO;
import com.comidaderuadev.api.exceptions.produto.NotFoundException;
import com.comidaderuadev.api.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<CategoriaDTO> findAll() {
        return categoriaRepository
                .findAll()
                .stream()
                .map((categoria) -> convertToDto(categoria))
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaDTO addCategoria(@RequestBody CategoriaDTO categoriaDTO) throws ParseException {
        Categoria categoria = convertToEntity(categoriaDTO);
        categoria.setId(0);
        return convertToDto(categoriaRepository.save(categoria)); 
    }

    @DeleteMapping("/{descricao}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategoria(@PathVariable String descricao) {
        Categoria categoria = categoriaRepository.findByDescricao(descricao);
        if (categoria == null) throw new NotFoundException("Produto não encontrado. Descricao: " + descricao);

        categoriaRepository.delete(categoria);
    }

    private CategoriaDTO convertToDto(Categoria categoria) {
        return modelMapper.map(categoria, CategoriaDTO.class);
    }

    private Categoria convertToEntity(CategoriaDTO categoriaDTO) throws ParseException {
        Categoria categoria = categoriaRepository.findByDescricao(categoriaDTO.getDescricao());

        if (categoria == null) {
            return modelMapper.map(categoriaDTO, Categoria.class);
        }

        return categoria;

    }

}
