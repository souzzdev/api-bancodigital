package br.com.cdb.bancodigital.exceptions;

public class DepositoException extends IllegalArgumentException {
    public DepositoException(String msg) {
        super(msg);
    }
}
