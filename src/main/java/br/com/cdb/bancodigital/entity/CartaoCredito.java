package br.com.cdb.bancodigital.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Entity
@DiscriminatorValue("CREDITO")
@Getter
@Setter
public class CartaoCredito extends Cartao {

    @OneToOne
    @JoinColumn(name = "conta_associada_id")
    private ContaCorrente contaAssociada;

    @Column(name = "limite", precision = 19, scale = 2)
    private BigDecimal limite;

    @Column(name = "gasto_mensal", precision = 19, scale = 2)
    private BigDecimal gastoMensal = BigDecimal.ZERO;

    public CartaoCredito() {}

    public CartaoCredito(ContaCorrente contaAssociada, BigDecimal limite) {
        this.contaAssociada = contaAssociada;
        this.limite = limite;
    }

    @Override
    public void pagar(BigDecimal valor, String senhaInformada) {
        if(!ativo)
            throw new RuntimeException("Cartão desativado");

        if(!senhaInformada.equals(senha))
            throw new RuntimeException("Senha invalida");

        if(valor.compareTo(limite) > 0)
            throw new RuntimeException("Limite insuficiente");

        limite = limite.subtract(valor);
        gastoMensal = gastoMensal.add(valor);
    }

    public void aplicarTaxaUtilizacao() {
        BigDecimal total = limite.add(gastoMensal);
        BigDecimal percentual = gastoMensal.multiply(BigDecimal.valueOf(100)).divide(total);

        if (percentual.compareTo(BigDecimal.valueOf(80)) > 0) {
            BigDecimal taxa = gastoMensal.multiply(BigDecimal.valueOf(0.5));
            contaAssociada.sacar(taxa);
        }
    }
}