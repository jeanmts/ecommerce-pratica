package org.serratec.ecommerce.repository;

import org.serratec.ecommerce.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
    @Query("select e from Endereco e where e.cep = :cep")
    Optional<List<Endereco>> findByCep(String cep);
}
