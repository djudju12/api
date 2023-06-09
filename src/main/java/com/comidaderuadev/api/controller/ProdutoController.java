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

import com.comidaderuadev.api.entity.produto.Produto;
import com.comidaderuadev.api.entity.produto.DTO.ProdutoDTO;
import com.comidaderuadev.api.exceptions.produto.NotFoundException;
import com.comidaderuadev.api.repository.CategoriaRepository;
import com.comidaderuadev.api.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
    
    private ProdutoRepository produtoRepository;
    private CategoriaRepository categoriaRepository;
    private ModelMapper modelMapper;

    @Autowired
    public ProdutoController(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository, ModelMapper modelMapper) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.modelMapper = modelMapper;
    }
    
    @GetMapping
    public List<ProdutoDTO> findAll(){
        return produtoRepository
                .findAll()
                .stream()
                .map(produto -> convertToDto(produto))
                .toList();
    }
    
    @GetMapping(value = "/{produtoId}")
    public ProdutoDTO findById(@PathVariable int produtoId){
        Produto p = produtoRepository     //   
                .findById(produtoId) //
                .orElseThrow(() -> new NotFoundException("Produto não encontrado. Id: " + produtoId));
        
        return convertToDto(p);

    }

    // Eu nao sei o que fazer com esse ParseExecption então só passei adiante
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDTO addProduto(@RequestBody ProdutoDTO produtoDTO) throws ParseException {
        Produto produto = convertToEntity(produtoDTO);
        produto.setId(0);
        Produto ProdutoCriado = produtoRepository.save(produto);
        return convertToDto(ProdutoCriado);
    }
    
    @PutMapping
    public ProdutoDTO updateProduto(@RequestBody ProdutoDTO produtoDTO) throws ParseException {
        Produto produto = convertToEntity(produtoDTO);
        Produto ProdutoCriado = produtoRepository.save(produto);
        return convertToDto(ProdutoCriado);
    }
    
    @DeleteMapping("/{produtoId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduto(@PathVariable int produtoId) throws ParseException {
        Produto produto = produtoRepository
                            .findById(produtoId)
                            .orElseThrow(() -> new NotFoundException("Produto não encontrado: Id: " + produtoId));

        produtoRepository.delete(produto);
    }
    
    private ProdutoDTO convertToDto(Produto produto) {
        ProdutoDTO produtoDTO = modelMapper.map(produto, ProdutoDTO.class);
        produtoDTO.setCategoria(produto.getCategoria().getDescricao());
        return produtoDTO;

    }

    private Produto convertToEntity(ProdutoDTO produtoDTO) throws ParseException {
        Produto produto = modelMapper.map(produtoDTO, Produto.class);
        produto.setCategoria(categoriaRepository.findByDescricao(produtoDTO.getCategoria()));
        return produto;
    }
}
