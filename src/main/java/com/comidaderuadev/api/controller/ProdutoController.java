package com.comidaderuadev.api.controller;

import java.text.ParseException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public List<ProdutoDTO> findAll() {
        return produtoService
                .findAll()
                .stream()
                .map(produto -> convertToDto(produto))
                .toList();
    }

    @GetMapping("/categorias")
    public List<CategoriaDTO> findAllCategorias() {
        return categoriaService
                .findAll()
                .stream()
                .map(categoria -> convertToDto(categoria))
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
    public ProdutoDTO addProduto(@RequestBody ProdutoDTO produtoDTO) throws ParseException {
        Produto produto = convertToEntity(produtoDTO);
        Produto ProdutoCriado = produtoService.add(produto);
        return convertToDto(ProdutoCriado);
    }

    @PostMapping("/categorias")
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaDTO addCategoria(@RequestBody CategoriaDTO categoriaDTO) throws ParseException {
        Categoria categoria = convertToEntity(categoriaDTO);
        Categoria categoriaCriada = categoriaService.add(categoria);
        return convertToDto(categoriaCriada);
    }

    @PutMapping("/{produtoId}")
    public ProdutoDTO updateProduto(@RequestBody ProdutoDTO produtoDTO, @PathVariable int produtoId) throws ParseException {
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

    @DeleteMapping("/categorias/{descricao}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCategoria(@PathVariable String descricao) {
        categoriaService.delete(descricao);
    }

    private ProdutoDTO convertToDto(Produto produto) {
        ProdutoDTO produtoDTO = modelMapper.map(produto, ProdutoDTO.class);
        Categoria categoria = produto.getCategoria();

        if (categoria == null) {
            produtoDTO.setCategoria("SEM CATEGORIA");
        } else {
            produtoDTO.setCategoria(produto.getCategoria().getDescricao());
        }
            
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

    private Categoria convertToEntity(CategoriaDTO categoriaDTO) throws ParseException {
        
        return modelMapper.map(categoriaDTO, Categoria.class);

    }
}
