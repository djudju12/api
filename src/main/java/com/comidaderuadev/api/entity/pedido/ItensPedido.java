package com.comidaderuadev.api.entity.pedido;

import java.util.ArrayList;
import java.util.List;

import com.comidaderuadev.api.entity.produto.Produto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "pedido_itens")
public class ItensPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_itens_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private List<Produto> produtos;

    ItensPedido() {}

    public ItensPedido(Pedido pedido) {
        this.pedido = pedido;
        this.produtos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public void addProduto(Produto produto) {
        if (this.produtos == null) 
            this.produtos = new ArrayList<>();
            
        this.produtos.add(produto);
    }

   @Override
   public String toString() {
      return "ItensPedido [id=" + id + ", pedido=" + pedido + ", produtos=" + produtos + "]";
   }

    
}
