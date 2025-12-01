package br.com.cdb.bancodigital.service;

import br.com.cdb.bancodigital.entity.ContaBancaria;
import br.com.cdb.bancodigital.enums.TipoCliente;
import br.com.cdb.bancodigital.repository.ContaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ManutencaoContaService {
    private final ContaRepository contaRepository;

    public ManutencaoContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public void aplicarTaxas () {
        List<ContaBancaria> contasBancarias = contaRepository.findAll();

        for (ContaBancaria conta : contasBancarias) {
            TipoCliente tipo = conta.getCliente().getTipoCliente();

            BigDecimal taxa = switch (tipo) {
                case COMUM -> BigDecimal.valueOf(12);
                case PREMIUM -> BigDecimal.valueOf(8);
                case SUPER -> BigDecimal.ZERO;
            };

            conta.sacar(taxa);
            contaRepository.save(conta);
        }
    }
}
