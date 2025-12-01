package br.com.cdb.bancodigital.exceptions.handler;

import br.com.cdb.bancodigital.exceptions.ClienteJaExisteException;
import br.com.cdb.bancodigital.exceptions.ClienteNaoEncontradoException;
import br.com.cdb.bancodigital.exceptions.DepositoException;
import br.com.cdb.bancodigital.exceptions.SaqueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ClienteJaExisteException.class)
    public ResponseEntity<String> handleClienteJaExisteException(ClienteJaExisteException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<String> handleClienteNaoEncontradoException(ClienteNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(DepositoException.class)
    public ResponseEntity<String> handleDepositoException(DepositoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(SaqueException.class)
    public ResponseEntity<String> handleSaqueException(SaqueException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
