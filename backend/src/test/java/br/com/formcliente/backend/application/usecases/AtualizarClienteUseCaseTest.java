package br.com.formcliente.backend.application.usecases;

import br.com.formcliente.backend.application.exception.ClienteDivergenteException;
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
class AtualizarClienteUseCaseTest {

    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private AtualizarClienteUseCase atualizarClienteUseCase;

    @Test
    void deveAtualizarClienteComSucesso() {
        ClienteEntity clienteExistente = new ClienteEntity();
        clienteExistente.setId(1L);
        clienteExistente.setNome("Cliente Antigo");

        when(repository.findById(1L)).thenReturn(Optional.of(clienteExistente));

        ClienteEntity clienteAtualizado = new ClienteEntity();
        clienteAtualizado.setId(1L);
        clienteAtualizado.setNome("Cliente Atualizado");

        when(repository.save(clienteAtualizado)).thenReturn(clienteAtualizado);

        ClienteEntity resultado = atualizarClienteUseCase.executa(1L, clienteAtualizado);

        // Validações
        assertNotNull(resultado);
        assertEquals("Cliente Atualizado", resultado.getNome());
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(clienteAtualizado);
    }

    @Test
    void deveLancarClienteNaoExisteExceptionQuandoClienteNaoExistir() {
        // Configura o cenário: cliente não encontrado.
        when(repository.findById(1L)).thenReturn(Optional.empty());

        ClienteEntity clienteAtualizado = new ClienteEntity();
        clienteAtualizado.setId(1L);
        clienteAtualizado.setNome("Cliente Atualizado");

        // Verifica se a exceção é lançada
        assertThrows(ClienteNaoExisteException.class, () -> {
            atualizarClienteUseCase.executa(1L, clienteAtualizado);
        });

        verify(repository, times(1)).findById(1L);
        verify(repository, never()).save(any());
    }

    @Test
    void deveLancarClienteDivergenteExceptionQuandoIdsDivergirem() {
        // Configura o cenário: cliente encontrado, mas os IDs divergem.
        ClienteEntity clienteExistente = new ClienteEntity();
        clienteExistente.setId(1L);
        clienteExistente.setNome("Cliente Antigo");

        when(repository.findById(1L)).thenReturn(Optional.of(clienteExistente));

        ClienteEntity clienteAtualizado = new ClienteEntity();
        clienteAtualizado.setId(2L); // ID divergente
        clienteAtualizado.setNome("Cliente Atualizado");

        // Verifica se a exceção é lançada
        assertThrows(ClienteDivergenteException.class, () -> {
            atualizarClienteUseCase.executa(1L, clienteAtualizado);
        });

        verify(repository, times(1)).findById(1L);
        verify(repository, never()).save(any());
    }
}