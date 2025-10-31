package org.serratec.ecommerce.dto;

import org.serratec.ecommerce.entity.Cliente;
import org.serratec.ecommerce.entity.Pedido;
import org.serratec.ecommerce.entity.Produto;
import org.serratec.ecommerce.enums.StatusPedido;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoRequesteDTO {

    private Cliente cliente;

    private StatusPedido statusPedido;

    private LocalDateTime dataHoraPedido;

    private List<Produto> produtos;

    private Double desconto;

    private Double valor;

    private Double valorTotal;
    public PedidoRequesteDTO() {
    }

    public PedidoRequesteDTO(Pedido pedido) {
        this.cliente = pedido.getCliente();
        this.statusPedido = pedido.getStatusPedido();
        this.dataHoraPedido = pedido.getDataHoraPedido();
        this.produtos = pedido.getProdutos();
        this.desconto = pedido.getDesconto();
        this.valor = pedido.getValor();
        this.valorTotal = pedido.getValorTotal();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public StatusPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public LocalDateTime getDataHoraPedido() {
        return dataHoraPedido;
    }

    public void setDataHoraPedido(LocalDateTime dataHoraPedido) {
        this.dataHoraPedido = dataHoraPedido;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Double getDesconto() {
        return desconto;
    }

    public void setDesconto(Double desconto) {
        this.desconto = desconto;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
