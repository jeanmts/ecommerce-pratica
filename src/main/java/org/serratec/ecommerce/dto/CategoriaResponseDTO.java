package org.serratec.ecommerce.dto;

import org.serratec.ecommerce.entity.Categoria;

public class CategoriaResponseDTO {


    private String nome;

    private String descricao;

    public CategoriaResponseDTO() {
    }

    public CategoriaResponseDTO(Categoria categoria) {
        this.nome = categoria.getNome();
        this.descricao = categoria.getDescricao();
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
