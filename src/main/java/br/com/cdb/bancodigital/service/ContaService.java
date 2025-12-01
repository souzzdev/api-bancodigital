package br.com.cdb.bancodigital.service;

import br.com.cdb.bancodigital.dto.ContaRequest;
import br.com.cdb.bancodigital.entity.Cliente;
import br.com.cdb.bancodigital.entity.ContaBancaria;
import br.com.cdb.bancodigital.entity.ContaCorrente;
import br.com.cdb.bancodigital.entity.ContaPoupanca;
import br.com.cdb.bancodigital.exceptions.DepositoException;
import br.com.cdb.bancodigital.exceptions.SaqueException;
import br.com.cdb.bancodigital.repository.ContaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ContaService {

    private final ContaRepository contaRepository;
    private final ClienteService clienteService;

    public ContaService(ContaRepository contaRepository, ClienteService clienteService) {
        this.contaRepository = contaRepository;
        this.clienteService = clienteService;
    }

    public ContaBancaria criarConta(ContaRequest request) {
        Cliente cliente = clienteService.buscarCliente(request.getCpfCliente());

        ContaBancaria conta;

        if (request.getTipoConta().equalsIgnoreCase("CORRENTE")) {
            ContaCorrente cc = new ContaCorrente();
            cc.setCliente(cliente);
            cc.setTipoCliente(cliente.getTipoCliente());
            cc.setLimiteChequeEspecial(request.getLimiteChequeEspecial());
            conta = cc;
        }

        else if (request.getTipoConta().equalsIgnoreCase("POUPANCA")) {
            ContaPoupanca pc = new ContaPoupanca();
            pc.setCliente(cliente);
            pc.setTipoCliente(cliente.getTipoCliente());
            pc.setTaxaRendimento(request.getTaxaRendimento());
            conta = pc;
        }

        else {
            throw new IllegalArgumentException("Tipo de conta inválido.");
        }

       return contaRepository.save(conta);
    }

    @Transactional
    public ContaBancaria fazerPix(Long contaOrigemId, Long contaDestinoId, BigDecimal valor) {
        ContaBancaria origem = contaRepository.findById(contaOrigemId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        ContaBancaria destino = contaRepository.findById(contaDestinoId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        origem.sacar(valor);
        destino.depositar(valor);

        contaRepository.save(origem);
        contaRepository.save(destino);
        return origem;
    }


    @Transactional
    public ContaBancaria depositar(Long numeroConta, BigDecimal valor) {

        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new DepositoException("Valor do depósito deve ser maior que zero");
        }

        ContaBancaria conta = contaRepository.findById(numeroConta)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        conta.depositar(valor);

        return contaRepository.save(conta);
    }

    @Transactional
    public ContaBancaria sacar(Long numeroConta, BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) == 0) {
            throw new SaqueException("O valor do saque deve ser maior que zero.");
        }

        if (valor.compareTo(valor) < 0) {
            throw new DepositoException("Saldo insuficiente!");
        }

        ContaBancaria conta = contaRepository.findById(numeroConta)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        conta.sacar(valor);
        return contaRepository.save(conta);
    }

    public ContaBancaria buscarConta(Long numeroConta) {
        return contaRepository.findById(numeroConta)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada."));
    }

    public void aplicarRendimento(Long numeroConta) {
        ContaBancaria conta = buscarConta(numeroConta);

        if(!(conta instanceof ContaPoupanca poupanca)) {
            throw new IllegalArgumentException("Apenas contas poupança possuem rendimento.");
        }

        poupanca.aplicarRendimento();

        contaRepository.save(conta);
    }

    public List<ContaBancaria> listarContas(Long numeroConta) {return contaRepository.findAll();}

}

