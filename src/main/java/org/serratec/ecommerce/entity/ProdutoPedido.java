package org.serratec.ecommerce.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

@Entity
@Table(name = "produto_pedido")
public class ProdutoPedido {

    @EmbeddedId
    private ProdutoPedidoPK id = new ProdutoPedidoPK();

    @Schema(description = "Valor do pedido!")
    @NotNull(message = "Valor do pedido não pode ser nulo!")
    @Column(name = "valor_pedido")
    private Double valorPedido;

    @Column(name = "valor_total_pedido")
    private Double valorTotalPedido;

    @Schema(description = "Porcentagem de desconto")
    @Positive(message = "A porcentagem de desconto não pode ser menor do que numero negativo!")
    @Min(value = 0, message = "A porcentagem de desconto não pode ser menor do que zero!")
    @NotNull(message = "O porcentagem de desconto deve ser informada!")
    private Double desconto;

    @Min(value = 1, message = "Pelo menos uma unidade do produto deve ser adicionada!")
    @NotNull(message = "A quantidade de produtos deve ser informada!")
    private Integer quantidade;

    public ProdutoPedido() {
    }

    public ProdutoPedido(Pedido pedido, Produto produto,Double valorPedido, Double valorTotalPedido, Double desconto, Integer quantidade) {
        this.id.setProduto(produto);
        this.id.setPedido(pedido);
        this.valorPedido = valorPedido;
        this.valorTotalPedido = valorTotalPedido;
        this.desconto = desconto;
        this.quantidade = quantidade;
    }

    public Double getValorPedido() {
        return valorPedido;
    }

    public void setValorPedido(Double valorPedido) {
        this.valorPedido = valorPedido;
    }

    public Double getValorTotalPedido() {
        return valorTotalPedido;
    }

    public void setValorTotalPedido(Double valorTotalPedido) {
        this.valorTotalPedido = valorTotalPedido;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public ProdutoPedidoPK getId() {
        return id;
    }

    public void setId(ProdutoPedidoPK id) {
        this.id = id;
    }


}
