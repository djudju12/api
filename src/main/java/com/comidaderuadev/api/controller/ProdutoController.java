package com.comidaderuadev.api.controller;

import java.text.ParseException;
import java.util.List;

import com.comidaderuadev.api.exceptions.NotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.comidaderuadev.api.entity.produto.Categoria;
import com.comidaderuadev.api.entity.produto.Produto;
import com.comidaderuadev.api.entity.produto.DTO.CategoriaDTO;
import com.comidaderuadev.api.entity.produto.DTO.ProdutoDTO;
import com.comidaderuadev.api.service.CategoriaService;
import com.comidaderuadev.api.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProdutoDTO> findAll() {
        return produtoService
                .findAll()
                .stream()
                .map(this::convertToDto)
                .toList();

    }

    @GetMapping("/categorias")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoriaDTO> findAllCategorias() {
        return categoriaService
                .findAll()
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    @GetMapping("/{produtoId}")
    public ProdutoDTO findById(@PathVariable int produtoId) {
        Produto p = produtoService.findById(produtoId);
        return convertToDto(p);
    }

    // Eu nao sei o que fazer com esse ParseExecption então só passei adiante
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDTO addProduto(@Valid @RequestBody ProdutoDTO produtoDTO) throws ParseException {
        Produto produto = convertToEntity(produtoDTO);
        Produto ProdutoCriado = produtoService.add(produto);
        return convertToDto(ProdutoCriado);
    }

    @PutMapping("/{produtoId}")
    public ProdutoDTO updateProduto(@Valid @RequestBody ProdutoDTO produtoDTO, @PathVariable int produtoId) throws ParseException {
        Produto produto = convertToEntity(produtoDTO);
        produto.setId(produtoId);
        Produto produtoAtualizado = produtoService.update(produto);
        return convertToDto(produtoAtualizado);
    }

    @DeleteMapping("/{produtoId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduto(@PathVariable int produtoId) {
        produtoService.delete(produtoId);
    }

    private ProdutoDTO convertToDto(Produto produto) {
        ProdutoDTO produtoDTO = modelMapper.map(produto, ProdutoDTO.class);
        Categoria categoria = produto.getCategoria();
        produtoDTO.setCategoria(categoria.getDescricao());
        return produtoDTO;

    }

    private Produto convertToEntity(ProdutoDTO produtoDTO) throws ParseException {
        Produto produto = modelMapper.map(produtoDTO, Produto.class);
        produto.setCategoria(categoriaService.findByDescricao(produtoDTO.getCategoria()));
        return produto; 
    }

    private CategoriaDTO convertToDto(Categoria categoria) {
        return modelMapper.map(categoria, CategoriaDTO.class);
    }
}
