package com.comidaderuadev.api.entity.mapper;

import com.comidaderuadev.api.entity.DTO.CategoriaDTO;
import com.comidaderuadev.api.entity.DTO.ProdutoDTO;
import com.comidaderuadev.api.entity.produto.Categoria;
import com.comidaderuadev.api.entity.produto.Produto;
import com.comidaderuadev.api.service.CategoriaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

class MapStructMapperProdutosTest {

    MapStructMapperProdutos MAPPER = Mappers.getMapper(MapStructMapperProdutos.class);

    final Integer PRODUTO_ID = 1;
    final Double PRODUTO_VALOR = 15.50;
    final String PRODUTO_DESCRICAO = "PÃO ÁRABE";
    final String CATEGORIA_DESCRICAO = "MEXICANA";
    Categoria categoria;
    @BeforeEach
    void setUp() {
        categoria = new Categoria(CATEGORIA_DESCRICAO);
    }

    @Test
    void produtoToProdutoDTO() {
        //given
        Produto produto = Produto.builder()
                .id(PRODUTO_ID)
                .produtoValor(PRODUTO_VALOR)
                .produtoDescricao(PRODUTO_DESCRICAO)
                .categoria(categoria)
                .build();

        //when
        ProdutoDTO produtoDTO = MAPPER.produtoToProdutoDTO(produto);

        //then
        assertThat(produtoDTO.getId()).isEqualTo(produto.getId());
        assertThat(produtoDTO.getProdutoValor()).isEqualTo(produto.getProdutoValor());
        assertThat(produtoDTO.getProdutoDescricao()).isEqualTo(produto.getProdutoDescricao());
        assertThat(produtoDTO.getCategoria()).isEqualTo(produto.getCategoria().getDescricao());
    }

    @Test
    void categoriaToCategoriaDTO() {
        //when
        CategoriaDTO categoriaDTO = MAPPER.categoriaToCategoriaDTO(categoria);

        //then
        assertThat(categoriaDTO.getDescricao()).isEqualTo(categoria.getDescricao());

    }

    @Test
    void produtoDTOToProduto() {
        //given
        CategoriaService service = Mockito.mock(CategoriaService.class);
        given(service.findByDescricao(anyString())).willReturn(categoria);
        ProdutoDTO produtoDTO = ProdutoDTO.builder()
                .id(PRODUTO_ID)
                .produtoValor(PRODUTO_VALOR)
                .produtoDescricao(PRODUTO_DESCRICAO)
                .categoria(CATEGORIA_DESCRICAO)
                .build();

        //when
        Produto returnedProduto = MAPPER.produtoDTOToProduto(produtoDTO, service);

        //then
        assertThat(returnedProduto.getId()).isEqualTo(produtoDTO.getId());
        assertThat(returnedProduto.getProdutoValor()).isEqualTo(produtoDTO.getProdutoValor());
        assertThat(returnedProduto.getProdutoDescricao()).isEqualTo(produtoDTO.getProdutoDescricao());
        assertThat(returnedProduto.getCategoria().getDescricao()).isEqualTo(produtoDTO.getCategoria());
    }

    @Test
    void mapCategoriaToString() {
    }

    @Test
    void mapStringToCategoria() {
    }
}