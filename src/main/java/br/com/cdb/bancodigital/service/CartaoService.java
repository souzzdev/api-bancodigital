package br.com.cdb.bancodigital.service;

import br.com.cdb.bancodigital.dto.CartaoRequest;
import br.com.cdb.bancodigital.entity.Cartao;
import br.com.cdb.bancodigital.entity.CartaoCredito;
import br.com.cdb.bancodigital.entity.CartaoDebito;
import br.com.cdb.bancodigital.entity.ContaBancaria;
import br.com.cdb.bancodigital.repository.CartaoRepository;
import br.com.cdb.bancodigital.repository.ContaRepository;
import org.springframework.stereotype.Service;


@Service
public class CartaoService {
    protected final CartaoRepository cartaoRepository;
    protected ContaRepository contaRepository;

    public CartaoService(CartaoRepository repo) {
        this.contaRepository = contaRepository;
        this.cartaoRepository = repo;
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
