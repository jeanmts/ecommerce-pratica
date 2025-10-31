package org.serratec.ecommerce.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.serratec.ecommerce.dto.CategoriaRequestDTO;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da categoria deve ser informada")@Size(max = 30, min = 3, message = "O nome da categoria deve conter no maximo 30 caracteres e no mínimo 3")
    private String nome;

    @NotBlank(message = "A descrição da categoria deve ser informada")@Size(max = 50, min = 3, message = "O descrição da categoria deve conter no maximo 50 caracteres e no mínimo 3")
    private String descricao;

    public Categoria() {
    }

    public Categoria(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
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
}
