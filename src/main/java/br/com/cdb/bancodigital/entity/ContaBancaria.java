package br.com.cdb.bancodigital.entity;

import br.com.cdb.bancodigital.enums.TipoCliente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Table(name = "conta_bancaria")
@Getter
@Setter
public abstract class ContaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cliente", nullable = false)
    private TipoCliente tipoCliente;

    @ManyToOne
    @JoinColumn(
            name = "cliente_cpf",
            referencedColumnName = "cpf",
            nullable = false
    )
    private Cliente cliente;

    @Column(name = "saldo", nullable = false, precision = 19, scale = 2)
    private BigDecimal saldo = BigDecimal.ZERO;

    public ContaBancaria() {}

    public ContaBancaria(TipoCliente tipoCliente, Cliente cliente) {
        this.tipoCliente = tipoCliente;
        this.cliente = cliente;
    }

    public abstract void depositar(BigDecimal valor);
    public abstract void sacar(BigDecimal valor);

    public Long getNumeroConta() {
        return this.id;
    }
}