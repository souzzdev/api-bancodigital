package br.com.cdb.bancodigital.exceptions;

public class ClienteJaExisteException extends RuntimeException {
    public ClienteJaExisteException(String msg) {
        super(msg);
    }
}
