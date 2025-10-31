package org.serratec.ecommerce.dto;

import org.serratec.ecommerce.entity.Categoria;
import org.serratec.ecommerce.entity.Pedido;
import org.serratec.ecommerce.entity.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDTO {

    private Long id;

    private String nome;

    private String descricao;

    private Double valor;

    private Integer estoque;


    private Categoria categoria;


    List<Pedido> pedidos = new ArrayList<>();

    public ProdutoDTO() {
    }

    public ProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.valor = produto.getValor();
        this.estoque = produto.getEstoque();
        this.categoria = produto.getCategoria();
        this.pedidos = produto.getPedidos();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }
}
