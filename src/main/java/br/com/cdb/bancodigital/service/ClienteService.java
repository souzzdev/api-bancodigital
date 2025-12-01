package br.com.cdb.bancodigital.service;

import br.com.cdb.bancodigital.entity.Cliente;
import br.com.cdb.bancodigital.exceptions.ClienteJaExisteException;
import br.com.cdb.bancodigital.exceptions.ClienteNaoEncontradoException;
import br.com.cdb.bancodigital.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente cadastrarCliente(Cliente cliente) {
        if (clienteRepository.existsById(cliente.getCpf())) {
            throw new ClienteJaExisteException("CPF já cadastrado.");
        }
        return clienteRepository.save(cliente);
    }


    public Cliente buscarCliente(String cpf) {
        return clienteRepository.findById(cpf)
                .orElseThrow(() -> new ClienteNaoEncontradoException(
                        "Cliente não encontrado com CPF: " + cpf
                ));
    }

    public Cliente atualizarCliente(String cpf, Cliente novosDados) {
        Cliente cliente = buscarCliente(cpf);

        if(!clienteRepository.existsById(cliente.getCpf())) {
            throw new ClienteNaoEncontradoException(
                    "Não é possível atualizar. Cliente não encontrado: " + cliente.getCpf()
            );
        }


        cliente.setNome(novosDados.getNome());
        cliente.setEndereco(novosDados.getEndereco());
        cliente.setDataNascimento(novosDados.getDataNascimento());
        cliente.setTipoCliente(novosDados.getTipoCliente());

        return clienteRepository.save(cliente);
    }

    public void deletaCliente(String cpf) {
        if(!clienteRepository.existsById(cpf)) {
            throw new ClienteNaoEncontradoException(
                    "Não é possível deletar. Cliente não encontrado: " + cpf
            );
        }

        clienteRepository.deleteById(cpf);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }
}
