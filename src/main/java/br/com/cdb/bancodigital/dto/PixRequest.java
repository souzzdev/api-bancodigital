package br.com.cdb.bancodigital.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PixRequest {
    private long idOrigem;
    private long idDestino;
    private BigDecimal valor;
}
