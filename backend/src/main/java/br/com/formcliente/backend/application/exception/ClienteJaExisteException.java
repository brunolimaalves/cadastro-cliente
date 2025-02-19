package br.com.formcliente.backend.application.exception;

public class ClienteJaExisteException extends RuntimeException {
    
    public ClienteJaExisteException() {
        super("Cliente já existe na base de dados.");
    }
    
    public ClienteJaExisteException(String message) {
        super(message);
    }
}
