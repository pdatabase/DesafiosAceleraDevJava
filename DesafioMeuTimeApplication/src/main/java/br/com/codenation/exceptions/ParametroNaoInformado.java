package br.com.codenation.exceptions;

public class ParametroNaoInformado extends RuntimeException{
    public ParametroNaoInformado(String message) {
        super(message);
    }
}