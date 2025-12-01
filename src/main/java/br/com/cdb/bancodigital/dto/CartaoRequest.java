package br.com.cdb.bancodigital.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartaoRequest {
    private Long contaId;
    private String senha;
    private String tipo;
}
