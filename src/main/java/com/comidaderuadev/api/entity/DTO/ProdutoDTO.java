package com.comidaderuadev.api.entity.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDTO {

    public ProdutoDTO() {}

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Id do produto", example = "26")
    @JsonProperty("produtoId")
    private int id;

    @Schema(description = "Identificador do produto", example = "Pão Árabe")
    @Size(min = 1, max = 200, message = "Descrição do produto deve possui de 1 até 200 caracteres.")
    private String produtoDescricao;

    @Schema(description = "Valor do produto", example = "12.00")
    @Positive(message = "Valor do produto deve ser positivo.")
    private double produtoValor;

    @Schema(description = "Nome da categoria do produto", example = "BRASILEIRA")
    @Size(min = 1, message = "Categoria não pode estar vazia.")
    private String categoria;

    @Builder
    public ProdutoDTO(int id, String produtoDescricao, double produtoValor, String categoria) {
        this.id = id;
        this.produtoDescricao = produtoDescricao;
        this.produtoValor = produtoValor;
        this.categoria = categoria;
    }

}
