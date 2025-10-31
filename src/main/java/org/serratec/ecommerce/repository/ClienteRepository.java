package org.serratec.ecommerce.repository;

import org.serratec.ecommerce.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("select c from Cliente c where c.email = :email")
    Optional<Cliente> findClienteByEmail(String email);

    @Query("select c from Cliente c where  c.cpf = :cpf")
    Optional<Cliente> findByCpf(String cpf);
}
