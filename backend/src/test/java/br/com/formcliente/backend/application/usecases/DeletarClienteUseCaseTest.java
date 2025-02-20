package br.com.formcliente.backend.application.usecases;

import br.com.formcliente.backend.application.exception.ClienteJaExisteException;
import br.com.formcliente.backend.application.exception.ClienteNaoExisteException;
import br.com.formcliente.backend.domain.repository.ClienteRepository;
import br.com.formcliente.backend.infra.persistence.ClienteEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeletarClienteUseCaseTest {

    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private DeletarClienteUseCase deletarClienteUseCase;

    @Test
    void testDeveExcluirClienteComSucesso() {
        Long id = 1L;
        ClienteEntity cliente = new ClienteEntity();
        cliente.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(cliente));
        deletarClienteUseCase.executa(id);
        verify(repository, times(1)).delete(cliente);
    }

    @Test
    void testDeveLancarClienteNaoExisteExceptionQuandoClienteNaoEncontrado() {

        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());
        assertThrows(ClienteNaoExisteException.class, () -> deletarClienteUseCase.executa(id));

        verify(repository, never()).delete(any());
    }
}