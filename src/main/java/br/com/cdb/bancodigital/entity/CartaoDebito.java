package br.com.cdb.bancodigital.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Entity
@DiscriminatorValue("DEBITO")
@Getter
@Setter
public class CartaoDebito extends Cartao {

    @ManyToOne(optional = false)
    @JoinColumn(name = "conta_associada_id")
    private ContaCorrente contaAssociada;

    @Column(name = "limite_diario", nullable = false, precision = 19, scale = 2)
    private BigDecimal limiteDiario = BigDecimal.ZERO;

    @Column(name = "gasto_hoje", precision = 19, scale = 2)
    private BigDecimal gastoHoje = BigDecimal.ZERO;

    public CartaoDebito() {}

    public CartaoDebito(ContaCorrente conta, BigDecimal limiteDiario) {
        this.contaAssociada = conta;
        this.limiteDiario = limiteDiario;
    }

    @Override
    public void pagar(BigDecimal valor, String senhaInformada) {
        if(!ativo)
            throw new RuntimeException("Cartão desativado");

        if(!senhaInformada.equals(senha))
            throw new RuntimeException("Senha invalida");


        if(gastoHoje.add(valor).compareTo(limiteDiario) > 0)
            throw new RuntimeException("Limite diario atingido");

        if (contaAssociada == null)
            throw new RuntimeException("Este cartão não está associado a nenhuma conta.");


        contaAssociada.sacar(valor);
        gastoHoje = gastoHoje.add(valor);
    }
}