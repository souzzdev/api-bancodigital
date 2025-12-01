package br.com.cdb.bancodigital.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransferenciaService {
    private final ContaService contaService;

    public TransferenciaService(ContaService contaService) {
        this.contaService = contaService;
    }

    @Transactional
    public void Transferir(Long idOrigem, Long idDestino, BigDecimal valor) {

        contaService.sacar(idOrigem, valor);
        contaService.depositar(idDestino, valor);
    }
}
