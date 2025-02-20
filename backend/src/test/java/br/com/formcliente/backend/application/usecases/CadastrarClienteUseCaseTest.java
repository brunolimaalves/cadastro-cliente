package br.com.formcliente.backend.application.usecases;

import br.com.formcliente.backend.application.exception.ClienteJaExisteException;
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
class CadastrarClienteUseCaseTest {

    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private CadastrarClienteUseCase criarClienteUseCase;

    @Test
    void deveCriarClienteComSucesso() {

        ClienteEntity novoCliente = new ClienteEntity();
        novoCliente.setCpf("12345678900");
        novoCliente.setNome("Cliente Novo");

        when(repository.findByCpf(novoCliente.getCpf())).thenReturn(Optional.empty());
        when(repository.save(novoCliente)).thenReturn(novoCliente);

        ClienteEntity resultado = criarClienteUseCase.executa(novoCliente);

        assertNotNull(resultado);
        assertEquals("Cliente Novo", resultado.getNome());
        verify(repository, times(1)).findByCpf(novoCliente.getCpf());
        verify(repository, times(1)).save(novoCliente);
    }

    @Test
    void deveLancarClienteJaExisteExceptionQuandoClienteJaExiste() {
        // Cenário: já existe um cliente com o mesmo CPF
        ClienteEntity clienteExistente = new ClienteEntity();
        clienteExistente.setCpf("12345678900");
        clienteExistente.setNome("Cliente Existente");

        when(repository.findByCpf(clienteExistente.getCpf()))
                .thenReturn(Optional.of(clienteExistente));

        assertThrows(ClienteJaExisteException.class, () -> {
            criarClienteUseCase.executa(clienteExistente);
        });

        verify(repository, times(1)).findByCpf(clienteExistente.getCpf());
        verify(repository, never()).save(any());
    }
}