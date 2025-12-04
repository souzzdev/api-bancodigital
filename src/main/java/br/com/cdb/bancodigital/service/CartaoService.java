package br.com.cdb.bancodigital.service;

import br.com.cdb.bancodigital.dto.CartaoRequest;
import br.com.cdb.bancodigital.entity.*;
import br.com.cdb.bancodigital.repository.CartaoRepository;
import br.com.cdb.bancodigital.repository.ContaRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class CartaoService {

    private final CartaoRepository cartaoRepository;

    private final ContaRepository contaRepository;

    @Autowired
    public CartaoService(CartaoRepository cartaoRepository, ContaRepository contaRepository) {
        this.cartaoRepository = cartaoRepository;
        this.contaRepository = contaRepository;
    }

    public Cartao criarCartao(CartaoRequest request) {
        ContaBancaria conta = contaRepository.findById(request.getContaId()).orElseThrow(() -> new RuntimeException("Conta inexistente"));

        Cartao novo;

        if (request.getTipo().equalsIgnoreCase("debito")) {
            novo = new CartaoDebito();
        }
        else if (request.getTipo().equalsIgnoreCase("credito")) {
            novo = new CartaoCredito();
        }
        else {
            throw new RuntimeException("Tipo invalido");
        }

        novo.setContaAssociada(conta);
        novo.setAtivo(true);
        novo.setSenha(request.getSenha());

        return cartaoRepository.save(novo);
    }

    public Cartao findById(Long id) {
        return cartaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cartão não encontrado"));
    }

    public void ativar(Long idCartao) {
        Cartao c = findById(idCartao);
        c.ativar();
        cartaoRepository.save(c);
    }

    public void desativar(Long idCartao) {
        Cartao c = findById(idCartao);
        c.desativar();
        cartaoRepository.save(c);
    }

    public void alterarSenha(Long idCartao, String atual, String nova) {
        Cartao c = findById(idCartao);
        c.alterarSenha(atual, nova);
        cartaoRepository.save(c);
    }
}
