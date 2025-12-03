package br.com.cdb.bancodigital.entity;

import br.com.cdb.bancodigital.enums.TipoCliente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cliente")
@Getter
@Setter
public class Cliente {


    @Column(name = "nome", nullable = false)
    private String nome;

    @Id
    @Column(name="cpf")
    private String cpf;

    @Column(name = "data_nascimento", nullable = false)
    private String dataNascimento;

    @Column(name = "endereco", nullable = false)
    private String endereco;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_cliente", nullable = false)
    private TipoCliente tipoCliente;


    public Cliente() {}

    public Cliente(String nome, String cpf, String dataNascimento, String endereco, TipoCliente tipoCliente) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.tipoCliente = tipoCliente;
    }
}