package br.com.cdb.bancodigital.controller;

import br.com.cdb.bancodigital.dto.ClienteRequest;
import br.com.cdb.bancodigital.entity.Cliente;
import br.com.cdb.bancodigital.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrarCliente(@RequestBody ClienteRequest request) {
        Cliente clienteSalvo = clienteService.cadastrarCliente(
                request.toEntity()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSalvo);
    }

    @GetMapping("{cpf}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable String cpf) {
        Cliente cliente = clienteService.buscarCliente(cpf);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("{cpf}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable String cpf, @RequestBody ClienteRequest request) {
        Cliente atualizado = clienteService.atualizarCliente(cpf,request.toEntity());
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("{cpf}")
    public ResponseEntity<Cliente> removerCliente(@PathVariable String cpf) {
        clienteService.deletaCliente(cpf);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes () {
        List<Cliente> clientes = clienteService.listarClientes();
        return ResponseEntity.ok(clientes);
    }
}
