package br.com.formcliente.backend.application.exception;

public class ClienteDivergenteException extends RuntimeException {

    public ClienteDivergenteException() {
        super("O ID do recurso na URL não confere com o ID informado no corpo da requisição.");
    }

    public ClienteDivergenteException(String message) {
        super(message);
    }
}
