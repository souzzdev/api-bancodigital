package br.com.cdb.bancodigital.dto;

public record SeguroCreateRequest(
        String tipoCliente,
        boolean ativarViagem
) {}
