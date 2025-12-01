package br.com.cdb.bancodigital.service;

import br.com.cdb.bancodigital.entity.CartaoCredito;
import br.com.cdb.bancodigital.enums.TipoCliente;
import br.com.cdb.bancodigital.repository.CartaoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartaoCreditoService extends CartaoService {
    private final CartaoRepository cartaoRepository;

    public CartaoCreditoService(CartaoRepository repo) {
        super(repo);
        this.cartaoRepository = repo;
    }

    public void pagar(Long idCartao, BigDecimal valor, String senhaInformada) {
        CartaoCredito c = (CartaoCredito) cartaoRepository.findById(idCartao)
                .orElseThrow(() -> new RuntimeException("Cartão não encontrado."));

        c.pagar(valor, senhaInformada);
        cartaoRepository.save(c);
    }

    public void aplicarTaxas(Long idCartao) {
        CartaoCredito c = (CartaoCredito) cartaoRepository.findById(idCartao)
                .orElseThrow(() -> new RuntimeException("Cartão não encontrado."));

        c.aplicarTaxaUtilizacao();
        cartaoRepository.save(c);
    }

    public void limiteInicial(Long idCartao) {
        CartaoCredito c = (CartaoCredito) cartaoRepository.findById(idCartao)
                .orElseThrow(() -> new RuntimeException("Cartão não encontrado."));

        TipoCliente tipo = c.getContaAssociada().getCliente().getTipoCliente();

        BigDecimal novoLimite = switch (tipo) {
            case COMUM -> BigDecimal.valueOf(1000.0);
            case PREMIUM ->  BigDecimal.valueOf(5000.0);
            case SUPER -> BigDecimal.valueOf(10000.0);
        };

        c.setLimite(novoLimite);
        cartaoRepository.save(c);
    }
}
