package br.com.cdb.bancodigital.dto;

import br.com.cdb.bancodigital.entity.Cliente;
import br.com.cdb.bancodigital.enums.TipoCliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteRequest {
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String endereco;
    private TipoCliente tipoCliente;

    public Cliente toEntity() {
        return new Cliente(nome, cpf, dataNascimento, endereco, tipoCliente);
    }
}
