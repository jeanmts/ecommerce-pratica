package org.serratec.ecommerce.service;

import org.serratec.ecommerce.dto.CategoriaDTO;
import org.serratec.ecommerce.dto.CategoriaRequestDTO;
import org.serratec.ecommerce.dto.CategoriaResponseDTO;
import org.serratec.ecommerce.entity.Categoria;
import org.serratec.ecommerce.exception.CategoriaDuplicadaException;
import org.serratec.ecommerce.exception.CategoriaNaoEncontradaException;
import org.serratec.ecommerce.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<CategoriaDTO> listarTodos() {

        List<Categoria> categorias = categoriaRepository.findAll();
        List<CategoriaDTO> categoriaDTOS = new ArrayList<>();

        for (Categoria categoria: categorias){
            categoriaDTOS.add(new CategoriaDTO(categoria));
        }
        return categoriaDTOS;
    }

    public CategoriaResponseDTO buscarPeloId(Long id){
        Optional<Categoria> categoria = categoriaRepository.findById(id);

        if (!categoria.isPresent()) {
            new CategoriaNaoEncontradaException("Categoria informada não existe!");
        }
        return new CategoriaResponseDTO(categoria.get());
    }

    @Transactional
    public CategoriaResponseDTO inserirCategoria(CategoriaRequestDTO categoriaRequestDTO) {

        System.out.println(categoriaRequestDTO.getNome());
        Optional<Categoria> optionalCategoria = categoriaRepository.findByNome(categoriaRequestDTO.getNome());


        if (!optionalCategoria.isEmpty()) {
            throw new CategoriaDuplicadaException("A categoria informada ja existe!");
        }

        Categoria categoria = new Categoria();
        categoria.setNome(categoriaRequestDTO.getNome());
        categoria.setDescricao(categoriaRequestDTO.getDescricao());

        categoriaRepository.save(categoria);

        CategoriaResponseDTO categoriaResponseDTO = new CategoriaResponseDTO(categoria);
        return categoriaResponseDTO;
    }

    @Transactional
    public CategoriaResponseDTO atualizar(CategoriaRequestDTO categoriaRequestDTO, Long id) {
        Optional<Categoria> optionalCategoria = categoriaRepository.findById(id);

        if (!optionalCategoria.isPresent()) {
            throw new CategoriaNaoEncontradaException("A categoria informada não foi encontrada");
        }

        Categoria categoria = new Categoria();
        categoria.setId(id);
        categoria.setNome(categoriaRequestDTO.getNome());
        categoria.setDescricao(categoriaRequestDTO.getDescricao());

        CategoriaResponseDTO categoriaResponseDTO = new CategoriaResponseDTO(categoria);
        categoriaRepository.save(categoria);

        return categoriaResponseDTO;
    }

    @Transactional
    public Void deletar(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new CategoriaNaoEncontradaException("A categoria informada não foi encontrada");
        }
         categoriaRepository.deleteById(id);
        return null;
    }

}
