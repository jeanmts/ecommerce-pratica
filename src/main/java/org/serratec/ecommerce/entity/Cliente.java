package org.serratec.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do cliente.")
    private Long id;

    @NotBlank(message = "O nome completo do cliente é obrigatório.")
    @Column(nullable = false)
    @Schema(description = "Nome completo do cliente.")
    private String nome;

    @Email(message = "O e-mail informado é inválido.")
    @NotBlank(message = "O e-mail do cliente é obrigatório.")
    @Column(nullable = false, unique = true)
    @Schema(description = "Endereço de e-mail único no cliente.")
    private String email;

    @CPF(message = "O CPF informado é inválido.")
    @NotBlank(message = "O CPF do cliente é obrigatório.")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter 11 dígitos numéricos.")
    @Column(nullable = false, unique = true, length = 11)
    @Schema(description = "CPF do cliente (use somente números, 11 dígitos).")
    private String cpf;

    @NotBlank(message = "O telefone do cliente é obrigatório.")
    @Column(nullable = false)
    @Schema(description = "Telefone do cliente (use apenas números).")
    private String telefone;

    @Column(name = "senha")
    @NotBlank(message = "Uma senha deve ser informada.")
    @Schema(description = "Informe a senha.")
    private String senha;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @Schema(description = "Lista de pedidos associados a este cliente.")
    private List<Pedido> pedidos;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    @Schema(description = "Endereço associado a este cliente.")
    private Endereco endereco;

    public Cliente() {
    }

    public Cliente(Long id, String nome, String email, String cpf, String telefone, String senha, List<Pedido> pedidos, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.telefone = telefone;
        this.senha = senha;
        this.pedidos = pedidos;
        this.endereco = endereco;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
