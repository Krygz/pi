package com.finan.orcamento.service;

import com.finan.orcamento.model.ClienteModel;
import com.finan.orcamento.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteModel> buscarCliente(){
        return clienteRepository.findAll();
    }

    public ClienteModel buscaId(Long id){
        Optional<ClienteModel> obj = clienteRepository.findById(id);
        if (obj.isPresent()) {
            return obj.get();
        } else {
            throw new RuntimeException("Cliente não encontrado");
        }
    }

    public ClienteModel cadastrarCliente(ClienteModel clienteModel){
        return clienteRepository.save(clienteModel);
    }

    public ClienteModel atualizaCliente(ClienteModel clienteModel, Long id){
        ClienteModel newClienteModel = buscaId(id);
        newClienteModel.setNome(clienteModel.getNome());
        newClienteModel.setCpf(clienteModel.getCpf());
        return clienteRepository.save(newClienteModel);
    }

    public void deletaCliente(Long id){
        clienteRepository.deleteById(id);
    }

    public List<ClienteModel> buscarPorNome(String nome) {
        return clienteRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<ClienteModel> buscarPorCpf(String cpf) {
        return clienteRepository.findByCpfContaining(cpf);
    }
}


