package br.com.formcliente.backend.application.usecases;

import br.com.formcliente.backend.application.exception.ClienteDivergenteException;
import br.com.formcliente.backend.application.exception.ClienteNaoExisteException;
import br.com.formcliente.backend.domain.repository.ClienteRepository;
import br.com.formcliente.backend.infra.persistence.ClienteEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeletarClienteUseCase {

    private final ClienteRepository repository;

    public DeletarClienteUseCase(ClienteRepository repository) {
        this.repository = repository;
    }

    public void executa(Long id) {
        Optional<ClienteEntity> clienteDB = repository.findById(id);
        if(clienteDB.isEmpty()) throw new ClienteNaoExisteException();
        repository.delete(clienteDB.get());
    }

}
