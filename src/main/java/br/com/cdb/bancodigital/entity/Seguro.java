package br.com.cdb.bancodigital.entity;

import br.com.cdb.bancodigital.enums.TipoCliente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "seguro")
public class Seguro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seguro_viagem_ativo", nullable = false)
    private boolean seguroViagemAtivo;

    @Column(name = "seguro_fraude_ativo", nullable = false)
    private boolean seguroFraudeAtivo;

    @Column(name = "valor_mensal", precision = 19, scale = 2, nullable = false)
    private BigDecimal valorMensal;

    @Column(name = "cobertura_fraude", precision = 19, scale = 2, nullable = false)
    private final BigDecimal coberturaFraude = BigDecimal.valueOf(5000.0);

    public Seguro() {}

    public Seguro(TipoCliente tipoCliente, boolean ativarViagem) {
        this.seguroFraudeAtivo = true;

        switch (tipoCliente) {
            case PREMIUM -> {
                this.seguroViagemAtivo = true;
                this.valorMensal = BigDecimal.ZERO;
            }
            case SUPER, COMUM -> {
                this.seguroViagemAtivo = ativarViagem;
                this.valorMensal = ativarViagem ? BigDecimal.valueOf(50.0) : BigDecimal.ZERO;
            }
            default -> throw new RuntimeException("Tipo de cliente inválido.");
        }
    }

    // Getters
    public BigDecimal calcularCustoMensal() {
        return valorMensal;
    }

    public boolean isSeguroViagemAtivo() {
        return seguroViagemAtivo;
    }

    public boolean isSeguroFraudeAtivo() {
        return seguroFraudeAtivo;
    }

    public BigDecimal getValorMensal() {
        return valorMensal;
    }

    public BigDecimal getCoberturaFraude() {
        return coberturaFraude;
    }
}