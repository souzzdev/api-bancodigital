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
@DiscriminatorValue("CORRENTE")
@Getter
@Setter
public class ContaCorrente extends ContaBancaria {

    @Column(name = "limite_cheque_especial", precision = 19, scale = 2)
    private BigDecimal limiteChequeEspecial = BigDecimal.ZERO;

    public ContaCorrente() {}

    public ContaCorrente(TipoCliente tipoCliente, Cliente cliente, BigDecimal limiteChequeEspecial) {
        super(tipoCliente, cliente);
        this.limiteChequeEspecial = limiteChequeEspecial;
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

        BigDecimal saldoDisponivel = this.getSaldo().add(limiteChequeEspecial);
        if (valor.compareTo(saldoDisponivel) > 0) {
            throw new SaqueException("Saldo insuficiente!");
        }

        this.setSaldo(this.getSaldo().subtract(valor));
    }
}