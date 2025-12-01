package br.com.cdb.bancodigital.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Schema(description = "DTO para solicitação de transferência tradicional")
public class TransferenciaRequest {

    @Schema(description = "ID da conta de origem", example = "1")
    private long idOrigem;

    @Schema(description = "ID da conta de destino", example = "2")
    private long idDestino;

    @Schema(description = "Valor da transferência", example = "300.00")
    private BigDecimal valor;
}