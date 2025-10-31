package org.serratec.ecommerce.service;

import org.serratec.ecommerce.dto.ClienteDTO;
import org.serratec.ecommerce.dto.ClienteRequestDTO;
import org.serratec.ecommerce.dto.ClienteResponseDTO;
import org.serratec.ecommerce.dto.EnderecoDTO;
import org.serratec.ecommerce.entity.Cliente;
import org.serratec.ecommerce.entity.Endereco;
import org.serratec.ecommerce.exception.CpfDuplicadoException;
import org.serratec.ecommerce.exception.EmailDuplicadoException;
import org.serratec.ecommerce.exception.ClienteNaoEncontradoException;
import org.serratec.ecommerce.exception.SenhaException;
import org.serratec.ecommerce.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoService enderecoService;

    public List<ClienteDTO> listarTodos() {
       List<Cliente> clientes = clienteRepository.findAll();
       List<ClienteDTO> clienteDTOS = new ArrayList<>();

       for (Cliente cliente: clientes) {
           clienteDTOS.add(new ClienteDTO(cliente));
       }
        return clienteDTOS;
    }

    public ClienteResponseDTO buscarPorId(Long id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);

        if (optionalCliente.isPresent()) {
            ClienteResponseDTO clienteResponseDTO = new ClienteResponseDTO(optionalCliente.get());
            return clienteResponseDTO;
        }
        throw  new ClienteNaoEncontradoException("Cliente informado nao existe!");
    }

        @Transactional
        public ClienteResponseDTO inserir (ClienteRequestDTO clienteRequestDTO) {
            Optional<Cliente> optionalCliente = clienteRepository.findClienteByEmail(clienteRequestDTO.getEmail());

            if (!clienteRequestDTO.getSenha().equals(clienteRequestDTO.getConfirmaSenha())) {
                throw  new SenhaException("A senha e confirma senha não são iguais");
            }

            if (optionalCliente.isPresent()){
                throw new EmailDuplicadoException("E-mail ja esta cadastrado");
            }

            Optional<Cliente> optional =clienteRepository.findByCpf(clienteRequestDTO.getCpf());

            if (optional.isPresent()) {
                throw  new CpfDuplicadoException("Cpf já esta cadastrado");
            }
            Cliente cliente = new Cliente();
            EnderecoDTO endereco = enderecoService.buscar(clienteRequestDTO.getEndereco().getCep());

            if (endereco.equals(null)) {
                throw new RuntimeException("Cep invalido");
            }

            Endereco endereco1 = new Endereco();
            endereco1.setCep(endereco.getCep());
            endereco1.setBairro(endereco.getBairro());
            endereco1.setComplemento(endereco.getComplemento());
            endereco1.setLocalidade(endereco.getLocalidade());
            endereco1.setLogradouro(endereco.getLogradouro());
            endereco1.setUf(endereco.getUf());

            cliente.setNome(clienteRequestDTO.getNome());
            cliente.setCpf(clienteRequestDTO.getCpf());
            cliente.setTelefone(clienteRequestDTO.getTelefone());
            cliente.setEndereco(endereco1);
            cliente.setSenha(clienteRequestDTO.getSenha());
            cliente.setEmail(clienteRequestDTO.getEmail());

            clienteRepository.save(cliente);

            return new ClienteResponseDTO(cliente);
    }

    public ClienteResponseDTO atualiza(ClienteRequestDTO clienteRequestDTO, Long id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);
        if (!clienteRequestDTO.getSenha().equals(clienteRequestDTO.getConfirmaSenha())) {
            throw new SenhaException("Senha e confirma senha, são diferentes!");
        }

        if (optionalCliente.isPresent()){
            Cliente cliente = new Cliente();

            cliente.setId(id);
            cliente.setNome(clienteRequestDTO.getNome());
            cliente.setCpf(clienteRequestDTO.getCpf());
            cliente.setEmail(clienteRequestDTO.getEmail());
            cliente.setEndereco(clienteRequestDTO.getEndereco());
            cliente.setSenha(clienteRequestDTO.getSenha());

            clienteRepository.save(cliente);
            return new ClienteResponseDTO(cliente);
        }
        throw new ClienteNaoEncontradoException("O cliente informado não existe!");
    }

    public void deletar(Long id) {
        Optional<Cliente> optionalCliente = clienteRepository.findById(id);

        if (!optionalCliente.isPresent()) {
            throw  new ClienteNaoEncontradoException("O cliente informado não existe!");
        }
        clienteRepository.deleteById(id);
    }
}
