package org.serratec.ecommerce.repository;

import org.serratec.ecommerce.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("select p from Produto p where p.nome = :nome")
    Optional<Produto> findByNome(String nome);
}
