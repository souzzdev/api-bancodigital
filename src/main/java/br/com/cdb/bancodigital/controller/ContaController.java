package br.com.cdb.bancodigital.controller;

import br.com.cdb.bancodigital.dto.ContaRequest;
import br.com.cdb.bancodigital.dto.DepositoRequest;
import br.com.cdb.bancodigital.dto.PixRequest;
import br.com.cdb.bancodigital.dto.SaqueRequest;
import br.com.cdb.bancodigital.entity.ContaBancaria;
import br.com.cdb.bancodigital.service.ContaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contas")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @PostMapping("/criar")
    public ResponseEntity<ContaBancaria> criarConta (@RequestBody ContaRequest request) {
        ContaBancaria contaCriada = contaService.criarConta(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(contaCriada);
    }

    @PutMapping("/pix")
    public ResponseEntity<ContaBancaria> pixConta (@RequestBody PixRequest request) {
        ContaBancaria pix = contaService.fazerPix(
                request.getIdOrigem(),
                request.getIdDestino(),
                request.getValor()
        );
        return ResponseEntity.status(HttpStatus.OK).body(pix);
    }

    @PutMapping("/depositar")
    public ResponseEntity<ContaBancaria> depositarConta (@RequestBody DepositoRequest request) {
        ContaBancaria deposito = contaService.depositar(
                request.getNumeroConta(),
                request.getValor()
        );
        return ResponseEntity.status(HttpStatus.OK).body(deposito);
    }

    @PutMapping("/sacar")
    public ResponseEntity<ContaBancaria> sacarConta(@RequestBody SaqueRequest request) {
        ContaBancaria saque = contaService.sacar(
                request.getNumeroConta(),
                request.getValor()
        );
        return ResponseEntity.status(HttpStatus.OK).body(saque);
    }

    @PutMapping("/{id}/rendimento")
    public ResponseEntity<String> aplicarRendimento(@PathVariable Long id) {
        contaService.aplicarRendimento(id);
        return ResponseEntity.ok("Rendimento aplilcado com sucesso.");
    }

    @GetMapping("{id}/listar")
    public ResponseEntity<Long> buscarConta(@PathVariable Long id){
        contaService.buscarConta(id);
        return ResponseEntity.status(HttpStatus.OK).body(id);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ContaBancaria>> listarContas(Long numeroConta){
        contaService.listarContas(numeroConta);
        return ResponseEntity.status(HttpStatus.OK).body(contaService.listarContas(numeroConta));
    }
}
