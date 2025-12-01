package br.com.cdb.bancodigital.controller;

import br.com.cdb.bancodigital.dto.TransferenciaRequest;
import br.com.cdb.bancodigital.service.TransferenciaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("transferencia")
public class TransferenciaController {

    private TransferenciaService transferenciaService;

    public TransferenciaController(TransferenciaService transferenciaService) {
        this.transferenciaService = transferenciaService;
    }

    @PostMapping("/transferir")
    public ResponseEntity<String> transferir(@RequestBody TransferenciaRequest request){
        transferenciaService.Transferir(
                request.getIdOrigem(),
                request.getIdDestino(),
                request.getValor()
        );

        return ResponseEntity.ok("Transferencia realizada com sucesso");
    }

}
