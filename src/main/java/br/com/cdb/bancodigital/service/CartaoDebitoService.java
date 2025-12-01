package br.com.cdb.bancodigital.service;

import br.com.cdb.bancodigital.entity.CartaoDebito;
import br.com.cdb.bancodigital.repository.CartaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartaoDebitoService extends CartaoService {

    private final CartaoRepository cartaoRepository;

    @Autowired
    public CartaoDebitoService(CartaoRepository repo) {
        super(repo);
        this.cartaoRepository = repo;
    }


    public void pagar(Long idCartao, BigDecimal valor, String senhaInformada) {
        CartaoDebito c = (CartaoDebito) cartaoRepository.findById(idCartao)
                .orElseThrow(() -> new RuntimeException("Cartão não encontrado."));

        c.pagar(valor, senhaInformada);
        cartaoRepository.save(c);
    }

    public void alterarLimiteDiario(Long idCartao, BigDecimal novoLimite) {
        CartaoDebito c = (CartaoDebito) cartaoRepository.findById(idCartao)
                .orElseThrow(() -> new RuntimeException("Cartão não encontrado."));

        c.setLimiteDiario(novoLimite);
        cartaoRepository.save(c);
    }

}
