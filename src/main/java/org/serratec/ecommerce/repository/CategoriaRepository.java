package org.serratec.ecommerce.repository;

import org.serratec.ecommerce.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    @Query("select c from Categoria c where c.nome = :nome")
    Optional<Categoria> findByNome(String nome);
}
