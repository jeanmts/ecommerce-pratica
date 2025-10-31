package org.serratec.ecommerce.repository;

import org.serratec.ecommerce.entity.ProdutoPedido;
import org.serratec.ecommerce.entity.ProdutoPedidoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoPedidoRepository extends JpaRepository<ProdutoPedido, ProdutoPedidoPK> {
}
