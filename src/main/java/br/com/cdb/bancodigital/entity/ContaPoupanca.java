package br.com.cdb.bancodigital.entity;

import br.com.cdb.bancodigital.enums.TipoCliente;
import br.com.cdb.bancodigital.exceptions.SaqueException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Entity
@DiscriminatorValue("POUPANCA")
@Getter
@Setter
public class ContaPoupanca extends ContaBancaria {

    @Column(name = "taxa_rendimento", precision = 5, scale = 4)
    private BigDecimal taxaRendimento = BigDecimal.valueOf(0.005); // 0.5% ao mês

    public ContaPoupanca() {}

    public ContaPoupanca(TipoCliente tipoCliente, Cliente cliente, BigDecimal taxaRendimento) {
        super(tipoCliente, cliente);
        this.taxaRendimento = taxaRendimento;
    }

    @Override
    public void depositar(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("O valor de depósito deve ser maior que zero!");
        }
        this.setSaldo(this.getSaldo().add(valor));
    }

    @Override
    public void sacar(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new SaqueException("O valor do saque deve ser maior que zero.");
        }

        if (valor.compareTo(this.getSaldo()) > 0) {
            throw new SaqueException("Saldo insuficiente!");
        }

        this.setSaldo(this.getSaldo().subtract(valor));
    }

    public void aplicarRendimento() {
        BigDecimal rendimento = this.getSaldo().multiply(taxaRendimento);
        this.setSaldo(this.getSaldo().add(rendimento));
    }
}