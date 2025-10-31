package org.serratec.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do endereço.")
    private Long id;

    @NotBlank(message = "O CEP precisa é obrigatório.")
    @Size(min = 8, max = 8, message = "O CEP deve conter 8 dígitos(use apenas númneros).")
    @Column(nullable = false, length = 8)
    @Schema(description = "CEP do endereço, usado para consulta via API externa.")
    private String cep;

    @Schema(description = "Logradouro retornado pela consulta de CEP.")
    private String logradouro;

    @Schema(description = "Bairro retornado pela consulta de CEP.")
    private String bairro;

    @Schema(description = "Cidade retornada pela consulta de CEP.")
    private String localidade;

    @Schema(description = "UF (estado) retornada pela consulta de CEP.")
    private String uf;

    @Schema(description = "Complemento do endereço.")
    private String complemento;

    @JsonIgnore
    @Schema(description = "Cliente associado a este endereço.")
    @OneToMany(mappedBy = "endereco")
    private List<Cliente> cliente;

    public Endereco() {
    }

    public Endereco(Long id, String cep, String logradouro, String bairro, String localidade, String uf, String complemento, List<Cliente> cliente) {
        this.id = id;
        this.cep = cep;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.localidade = localidade;
        this.uf = uf;
        this.complemento = complemento;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public List<Cliente> getCliente() {
        return cliente;
    }

    public void setCliente(List<Cliente> cliente) {
        this.cliente = cliente;
    }
}
