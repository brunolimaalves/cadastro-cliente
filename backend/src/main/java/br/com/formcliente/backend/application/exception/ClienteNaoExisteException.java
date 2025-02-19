package br.com.formcliente.backend.application.exception;

public class ClienteNaoExisteException extends RuntimeException {

    public ClienteNaoExisteException() {
        super("Cliente não existe na base de dados.");
    }

    public ClienteNaoExisteException(String message) {
        super(message);
    }
}
