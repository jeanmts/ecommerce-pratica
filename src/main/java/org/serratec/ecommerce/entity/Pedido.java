package org.serratec.ecommerce.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.serratec.ecommerce.enums.StatusPedido;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name ="id_cliente")
    private Cliente cliente;

    @Schema(description = "Status atual do pedido", example = "PENDENTE")
    @NotNull(message = "O status é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedido statusPedido;

    @NotBlank(message = "A data do pedido deve ser informada")
    private LocalDateTime dataHoraPedido;

    @Schema(description = "Lista de itens (ProdutoPedido) incluídos no pedido")
    @NotEmpty(message = "O pedido deve conter pelo menos um item")
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "pedido_produto", joinColumns = @JoinColumn(name = "id_pedido"), inverseJoinColumns = @JoinColumn( name = "id_produto"))
    private List<Produto> produtos;

    public Pedido() {
    }

    public Pedido(Long id, Cliente cliente, StatusPedido statusPedido, LocalDateTime dataHoraPedido, List<Produto> produtos) {
        this.id = id;
        this.cliente = cliente;
        this.statusPedido = statusPedido;
        this.dataHoraPedido = dataHoraPedido;
        this.produtos = produtos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
