package org.serratec.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do produto deve ser informado!")@Size(min = 3, max = 50, message = "O nome do produto deve ser informado, e deve conter no mínimo 3 caracteres e no maximo 50!")
    private String nome;

    @NotBlank(message = "Uma descrição do produto deve ser informado!")@Size(min = 5, max = 100, message = "Uma descrição produto deve ser informado, e deve conter no mínimo 5 caracteres e no maximo 100!")
    private String descricao;

    @NotNull(message = "O valor do produto deve ser informado")@Positive(message = "O valor do produto deve ser um numero maior do que 0")
    private Double valor;

    @NotNull(message = "O estoque do produto deve ser informado!")@Min(value = 1, message = "O estoque do produto precisa conter pelo menos 1")
    private Integer estoque;

    @NotNull(message = "Categoria é obrigatória")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @JsonIgnore
    @ManyToMany( mappedBy = "produtos", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<Pedido> pedidos = new ArrayList<>();

    public Produto() {
    }

    public Produto(Long id, String nome, String descricao, Double valor, Integer estoque, Categoria categoria, List<Pedido> pedidos) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.estoque = estoque;
        this.categoria = categoria;
        this.pedidos = pedidos;
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
