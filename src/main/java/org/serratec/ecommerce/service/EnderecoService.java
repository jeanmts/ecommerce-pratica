package org.serratec.ecommerce.service;

import org.serratec.ecommerce.dto.EnderecoDTO;
import org.serratec.ecommerce.entity.Endereco;
import org.serratec.ecommerce.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public EnderecoDTO buscar(String cep) {
        Optional<List<Endereco>> endereco = enderecoRepository.findByCep(cep);

        if(endereco.isPresent()) {
            return new EnderecoDTO(endereco.get().get(0));
        } else {
            RestTemplate restTemplate = new RestTemplate();
            String uri="https://viacep.com.br/ws/"+cep+"/json";
            Optional<Endereco> enderecoViaCep = Optional.ofNullable
                    (restTemplate.getForObject(uri, Endereco.class));

            if(enderecoViaCep.get().getCep() != null) {
                String cepSemTraco = enderecoViaCep.get().getCep().replaceAll("-", "");
                enderecoViaCep.get().setCep(cepSemTraco);
                return inserir(enderecoViaCep.get());
            } else {
                return null;
            }
        }
    }

    private EnderecoDTO inserir(Endereco endereco) {
        return new EnderecoDTO(enderecoRepository.save(endereco));
    }
}
