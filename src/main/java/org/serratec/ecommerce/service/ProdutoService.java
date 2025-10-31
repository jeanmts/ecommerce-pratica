package org.serratec.ecommerce.service;

import org.serratec.ecommerce.dto.ProdutoDTO;

import org.serratec.ecommerce.dto.ProdutoRequestDTO;
import org.serratec.ecommerce.dto.ProdutoResponseDTO;
import org.serratec.ecommerce.entity.Produto;
import org.serratec.ecommerce.exception.ProdutoDuplicadoException;
import org.serratec.ecommerce.exception.ProdutoNaoEncontradoException;
import org.serratec.ecommerce.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoDTO> listarProduto() {
        List<Produto> produtos = produtoRepository.findAll();
        List<ProdutoDTO> produtoDTOS = new ArrayList<>();

        for (Produto produto : produtos) {
            produtoDTOS.add(new ProdutoDTO(produto));
        }
        return produtoDTOS;
    }

    public ProdutoResponseDTO buscarPeloId(Long id){
        Optional<Produto> optionalProduto = produtoRepository.findById(id);

        if (!optionalProduto.isPresent()) {
            throw new ProdutoNaoEncontradoException("O produto informado não existe!");
        }
        ProdutoResponseDTO produtoResponseDTO = new ProdutoResponseDTO(optionalProduto.get());

        return  produtoResponseDTO;
    }

    public ProdutoResponseDTO inserirProduto(ProdutoRequestDTO produtoRequestDTO) {
        Optional<Produto> optionalProduto = produtoRepository.findByNome(produtoRequestDTO.getNome());

        if (optionalProduto.isPresent()) {
            throw new ProdutoDuplicadoException("O produto informado já esta cadastrado!");
        }
        Produto produto = new Produto();
        produto.setNome(produtoRequestDTO.getNome());
        produto.setDescricao(produtoRequestDTO.getDescricao());
        produto.setCategoria(produtoRequestDTO.getCategoria());
        produto.setValor(produtoRequestDTO.getValor());

        produtoRepository.save(produto);

        return new ProdutoResponseDTO(produto);
    }
    public ProdutoResponseDTO atualizarProduto(ProdutoRequestDTO produtoRequestDTO,Long id) {
        Optional<Produto> optionalProduto = produtoRepository.findById(id);

        if (!optionalProduto.isPresent()) {
            throw new ProdutoNaoEncontradoException("O produto informado não existe!");
        }

        Produto produto = new Produto();
        produto.setId(id);
        produto.setNome(produtoRequestDTO.getNome());
        produto.setDescricao(produtoRequestDTO.getDescricao());
        produto.setCategoria(produtoRequestDTO.getCategoria());
        produto.setValor(produtoRequestDTO.getValor());

        produtoRepository.save(produto);

        return new ProdutoResponseDTO(produto);
    }

    public void deletar(Long id) {
        Optional<Produto> optionalProduto = produtoRepository.findById(id);
        if (!optionalProduto.isPresent()) {
            throw new ProdutoNaoEncontradoException("O produto informado não existe!");
        }
        produtoRepository.deleteById(id);
    }

}
