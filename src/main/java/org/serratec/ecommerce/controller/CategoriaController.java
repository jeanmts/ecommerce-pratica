package org.serratec.ecommerce.controller;

import jakarta.validation.Valid;
import org.serratec.ecommerce.dto.CategoriaDTO;
import org.serratec.ecommerce.dto.CategoriaRequestDTO;
import org.serratec.ecommerce.dto.CategoriaResponseDTO;
import org.serratec.ecommerce.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listaTodasCategorias() {
        return ResponseEntity.ok(categoriaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> listaPeloId(@PathVariable Long id){
        return ResponseEntity.ok(categoriaService.buscarPeloId(id));
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> inserir (@Valid @RequestBody CategoriaRequestDTO categoriaRequestDTO){
        return ResponseEntity.ok(categoriaService.inserirCategoria(categoriaRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> atualizar(@Valid @RequestBody CategoriaRequestDTO categoriaRequestDTO, @PathVariable Long id) {
        return ResponseEntity.ok(categoriaService.atualizar(categoriaRequestDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        categoriaService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
