package br.com.cdb.bancodigital.controller;

import br.com.cdb.bancodigital.dto.AlterarSenhaRequest;
import br.com.cdb.bancodigital.dto.CartaoRequest;
import br.com.cdb.bancodigital.dto.SenhaPagamentoRequest;
import br.com.cdb.bancodigital.entity.Cartao;
import br.com.cdb.bancodigital.entity.CartaoDebito;
import br.com.cdb.bancodigital.service.CartaoDebitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cartaodebito")
public class CartaoDebitoController {
    @Autowired
    private CartaoDebitoService cartaoDebitoService;

    @PostMapping
    public ResponseEntity<Cartao> criar(@RequestBody CartaoRequest request){
        cartaoDebitoService.criarCartao(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}/listar-cartao")
    public ResponseEntity<CartaoDebito> buscarCartao(@PathVariable Long id){
        cartaoDebitoService.findById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/alterar-senha")
    public ResponseEntity<Cartao> alterarSenha(@PathVariable Long id, @RequestBody AlterarSenhaRequest request) {
        cartaoDebitoService.alterarSenha(
                id,
                request.getSenhaAtual(),
                request.getNovaSenha()
        );
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/ativar-cartao")
    public ResponseEntity<Void> ativarCartao(@PathVariable Long id) {
        cartaoDebitoService.ativar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/desativar-cartao")
    public ResponseEntity<Void> desativarCartao(@PathVariable Long id) {
        cartaoDebitoService.desativar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/pagar")
    public ResponseEntity<CartaoDebito> pagar(@PathVariable Long id, BigDecimal valor, SenhaPagamentoRequest request) {
        cartaoDebitoService.pagar(
                id,
                valor,
                request.getSenha()
        );

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/alterar-limite-diario")
    public ResponseEntity<CartaoDebito>  alterarLimiteDiario(@PathVariable Long id, @RequestBody BigDecimal novoLimite) {
        cartaoDebitoService.alterarLimiteDiario(
                id,
                novoLimite
        );
        return ResponseEntity.noContent().build();
    }



}
