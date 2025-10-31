package org.serratec.ecommerce.service;

import org.serratec.ecommerce.dto.PedidoRequesteDTO;
import org.serratec.ecommerce.dto.PedidoResponseDTO;
import org.serratec.ecommerce.dto.ProdutoDTO;
import org.serratec.ecommerce.dto.ProdutoResponseDTO;
import org.serratec.ecommerce.entity.Pedido;
import org.serratec.ecommerce.exception.PedidoNaoEncontradoException;
import org.serratec.ecommerce.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;


    public  List<PedidoResponseDTO> listarTodosPedidos(){
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<PedidoResponseDTO> pedidoResponseDTOS = new ArrayList<>();

        for (Pedido pedido: pedidos){
            pedidoResponseDTOS.add(new PedidoResponseDTO(pedido));
        }

        return pedidoResponseDTOS;
    }

    public PedidoResponseDTO buscarPeloId(Long id){
        Optional<Pedido> pedido = pedidoRepository.findById(id);

        if (!pedido.isPresent()) {
            throw new PedidoNaoEncontradoException("O pedido informado não existe!");
        }
        return new PedidoResponseDTO(pedido.get());
    }

    public PedidoResponseDTO inserirPedido(PedidoRequesteDTO pedidoRequesteDTO){

        return
    }
}
