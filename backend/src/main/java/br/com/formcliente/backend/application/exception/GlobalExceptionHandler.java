package br.com.formcliente.backend.application.exception;

import br.com.formcliente.backend.presentation.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ClienteJaExisteException.class)
    public ResponseEntity<ApiResponse> handleClienteJaCadastradoException(ClienteJaExisteException ex) {
        ApiResponse errorResponse = new ApiResponse();
        errorResponse.setStatus(HttpStatus.CONFLICT.value());
        errorResponse.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(ClienteNaoExisteException.class)
    public ResponseEntity<ApiResponse> handleClienteNaoExisteException(ClienteNaoExisteException ex) {
        ApiResponse errorResponse = new ApiResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(ClienteDivergenteException.class)
    public ResponseEntity<ApiResponse> handleClienteDivergenteException(ClienteDivergenteException ex) {
        ApiResponse errorResponse = new ApiResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
