package org.serratec.ecommerce.entity;

import jakarta.persistence.*;

import java.io.Serializable;


@Embeddable
public class ProdutoPedidoPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    public ProdutoPedidoPK() {
    }
    public ProdutoPedidoPK(Produto produto, Pedido pedido) {
        this.produto = produto;
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
