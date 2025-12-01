package br.com.cdb.bancodigital.controller;

import br.com.cdb.bancodigital.dto.SeguroCreateRequest;
import br.com.cdb.bancodigital.entity.Seguro;
import br.com.cdb.bancodigital.enums.TipoCliente;
import br.com.cdb.bancodigital.service.SeguroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/seguro")
public class SeguroController {
    @Autowired
    private SeguroService seguroService;

    @PostMapping
    public ResponseEntity<Seguro> criar(@RequestBody SeguroCreateRequest request){
         TipoCliente tipo = TipoCliente.valueOf(request.tipoCliente());

         Seguro seguro = new Seguro(tipo, request.ativarViagem());

         Seguro salvo = seguroService.criarSeguro(seguro);

         return ResponseEntity.ok(salvo);
    }

    // Buscar detalhes formatados
    @GetMapping("/{id}/detalhes")
    public ResponseEntity<String> detalhes(@PathVariable Integer id) {
        String detalhes = seguroService.gerarDetalhes(id);
        return ResponseEntity.ok(detalhes);
    }


    // Ativar seguro de viagem
    @PutMapping("/{id}/ativar-viagem")
    public ResponseEntity<Seguro> ativar(@PathVariable Integer id) {
        Seguro s = seguroService.ativarSeguroViagem(id);
        return ResponseEntity.ok(s);
    }


    // Desativar seguro de viagem
    @PutMapping("/{id}/desativar-viagem")
    public ResponseEntity<Seguro> desativar(@PathVariable Integer id) {
        Seguro s = seguroService.desativarSeguroViagem(id);
        return ResponseEntity.ok(s);
    }


    // Buscar seguro individual
    @GetMapping("/{id}")
    public ResponseEntity<Seguro> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(seguroService.buscarPorId(id));
    }
}
