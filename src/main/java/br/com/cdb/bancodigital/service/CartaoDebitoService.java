package br.com.cdb.bancodigital.service;

import br.com.cdb.bancodigital.entity.Cartao;
import br.com.cdb.bancodigital.entity.CartaoDebito;
import br.com.cdb.bancodigital.repository.CartaoRepository;
import br.com.cdb.bancodigital.repository.ContaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartaoDebitoService extends CartaoService {

    @Autowired
    public CartaoDebitoService(CartaoRepository cartaoRepository, ContaRepository contaRepository) {
        super(cartaoRepository, contaRepository);
    }

    @Transactional
    public void pagar(Long idCartao, BigDecimal valor, String senhaInformada) {
        Cartao cartao = findById(idCartao);

        if (!(cartao instanceof CartaoDebito)) {
            throw new RuntimeException("Este ID pertence a um cartão de crédito. Use a rota de cartão de crédito para pagamentos.");
        }

        CartaoDebito cartaoDebito = (CartaoDebito) cartao;
        cartaoDebito.pagar(valor, senhaInformada);
        getCartaoRepository().save(cartaoDebito);
    }

    public void alterarLimiteDiario(Long idCartao, BigDecimal novoLimite) {
        CartaoDebito c = (CartaoDebito) findById(idCartao);

        c.setLimiteDiario(novoLimite);
        getCartaoRepository().save(c);
    }

}
