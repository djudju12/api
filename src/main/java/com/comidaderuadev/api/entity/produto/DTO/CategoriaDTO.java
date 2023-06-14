package com.comidaderuadev.api.entity.produto.DTO;

import io.swagger.v3.oas.annotations.media.Schema;

public class CategoriaDTO {

    @Schema(description = "Nome da categoria do produto", example = "COMIDA BRASILEIRA")
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    
}
