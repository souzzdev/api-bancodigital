package br.com.cdb.bancodigital.controller;

import br.com.cdb.bancodigital.dto.AlterarSenhaRequest;
import br.com.cdb.bancodigital.dto.CartaoRequest;
import br.com.cdb.bancodigital.dto.PagamentoRequest;
import br.com.cdb.bancodigital.entity.Cartao;
import br.com.cdb.bancodigital.entity.CartaoCredito;
import br.com.cdb.bancodigital.service.CartaoCreditoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/cartaocredito")
public class CartaoCreditoController {
    @Autowired
    private CartaoCreditoService cartaoCreditoService;

    @PostMapping
    public ResponseEntity<Cartao> criar(@RequestBody CartaoRequest request) {
        cartaoCreditoService.criarCartao(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}/listar-cartao")
    public ResponseEntity<Cartao> buscarCartao(@PathVariable Long id) {
        cartaoCreditoService.findById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/alterar-senha")
    public ResponseEntity<Cartao> alterarSenha(@PathVariable Long id, @RequestBody AlterarSenhaRequest request) {
        cartaoCreditoService.alterarSenha(
                id,
                request.getSenhaAtual(),
                request.getNovaSenha()
        );

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/ativar-cartao")
    public ResponseEntity<Void> ativarCartao(@PathVariable Long id) {
        cartaoCreditoService.ativar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/desativar-cartao")
    public ResponseEntity<Void> desativarCartao(@PathVariable Long id) {
        cartaoCreditoService.desativar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/pagar")
    public ResponseEntity<CartaoCredito> pagar(@PathVariable Long id, @RequestBody PagamentoRequest request) {
        cartaoCreditoService.pagar(
                id,
                request.getValor(),
                request.getSenha()
        );

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/aplicar-taxas")
    public ResponseEntity<CartaoCredito> aplicarTaxa(@PathVariable Long id) {
        cartaoCreditoService.aplicarTaxas(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/limite-inicial")
    public ResponseEntity<CartaoCredito> limiteInicial(@PathVariable Long id) {
        cartaoCreditoService.limiteInicial(id);
        return ResponseEntity.noContent().build();
    }

}
