package br.com.cdb.bancodigital.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cartao")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter
@Setter
public abstract class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "senha", nullable = false, length = 4)
    protected String senha;

    @Column(name = "ativo", nullable = false)
    protected boolean ativo = true;

    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "conta_associada_id", nullable = false)
    private ContaBancaria contaAssociada;

    public abstract void pagar(BigDecimal valor, String senhaInformada);

    public void ativar() {
        this.ativo = true;
    }

    public void desativar() {
        this.ativo = false;
    }

    public void alterarSenha(String senhaAtual, String novaSenha) {
        if(!senhaAtual.equals(this.senha)) {
            throw new RuntimeException("Senha atual incorreta.");
        }

        if(!novaSenha.matches("\\d{4}")) {
            throw new RuntimeException("Senha invalida, deve conter no minimo 4 caracteres");
        }

        this.senha = novaSenha;
    }
}