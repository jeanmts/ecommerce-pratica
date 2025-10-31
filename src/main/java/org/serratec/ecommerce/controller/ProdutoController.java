package org.serratec.ecommerce.controller;

import jakarta.validation.Valid;
import org.serratec.ecommerce.dto.ProdutoDTO;
import org.serratec.ecommerce.dto.ProdutoRequestDTO;
import org.serratec.ecommerce.dto.ProdutoResponseDTO;
import org.serratec.ecommerce.service.CategoriaService;
import org.serratec.ecommerce.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> listarTodos(){
        return ResponseEntity.ok(produtoService.listarProduto());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarPeloId(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarPeloId(id));
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> inserirProduto(@Valid @RequestBody ProdutoRequestDTO produtoRequestDTO) {
        ProdutoResponseDTO produtoResponseDTO = produtoService.inserirProduto(produtoRequestDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(produtoResponseDTO.getId())
                .toUri();
        return ResponseEntity.created(uri).body(produtoResponseDTO);

    }

    @PutMapping("{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(@Valid @RequestBody ProdutoRequestDTO produtoRequestDTO, @PathVariable Long id) {
        return ResponseEntity.ok(produtoService.atualizarProduto(produtoRequestDTO, id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id) {
        produtoService.deletar(id);
       return ResponseEntity.noContent().build();
    }
}
