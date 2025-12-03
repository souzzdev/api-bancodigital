package br.com.cdb.bancodigital.service;

import br.com.cdb.bancodigital.entity.Cartao;
import br.com.cdb.bancodigital.entity.CartaoCredito;
import br.com.cdb.bancodigital.entity.CartaoDebito;
import br.com.cdb.bancodigital.enums.TipoCliente;
import br.com.cdb.bancodigital.repository.CartaoRepository;
import br.com.cdb.bancodigital.repository.ContaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartaoCreditoService extends CartaoService {

    @Autowired
    public CartaoCreditoService(CartaoRepository cartaoRepository, ContaRepository contaRepository) {
        super(cartaoRepository, contaRepository);
    }

    @Transactional
    public void pagar(Long idCartao, BigDecimal valor, String senhaInformada) {
        Cartao cartao = findById(idCartao);

        if (!(cartao instanceof CartaoCredito)) {
            throw new RuntimeException("Este ID pertence a um cartão de débito. Use a rota de cartão de crédito para pagamentos.");
        }

        CartaoCredito cartaoCredito = (CartaoCredito) cartao;
        cartaoCredito.pagar(valor, senhaInformada);
        getCartaoRepository().save(cartaoCredito);
    }

    public void aplicarTaxas(Long idCartao) {
        CartaoCredito c = (CartaoCredito) findById(idCartao);

        c.aplicarTaxaUtilizacao();
        getCartaoRepository().save(c);
    }

    public void limiteInicial(Long idCartao) {
        CartaoCredito c = (CartaoCredito) findById(idCartao);

        TipoCliente tipo = c.getContaAssociada().getCliente().getTipoCliente();

        BigDecimal novoLimite = switch (tipo) {
            case COMUM -> BigDecimal.valueOf(1000.0);
            case PREMIUM ->  BigDecimal.valueOf(5000.0);
            case SUPER -> BigDecimal.valueOf(10000.0);
        };

        c.setLimite(novoLimite);
        getCartaoRepository().save(c);
    }
}
