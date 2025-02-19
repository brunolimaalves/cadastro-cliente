package br.com.formcliente.backend.application.usecases;

import br.com.formcliente.backend.application.exception.ClienteDivergenteException;
import br.com.formcliente.backend.application.exception.ClienteNaoExisteException;
import br.com.formcliente.backend.domain.repository.ClienteRepository;
import br.com.formcliente.backend.infra.persistence.ClienteEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AtualizarClienteUseCase {

    private final ClienteRepository repository;

    public AtualizarClienteUseCase(ClienteRepository repository) {
        this.repository = repository;
    }

    public ClienteEntity executa(Long id, ClienteEntity entity) {
        Optional<ClienteEntity> clienteDB = repository.findById(id);
        if(clienteDB.isEmpty()) throw new ClienteNaoExisteException();
        if(!clienteDB.get().getId().equals(entity.getId())) throw new ClienteDivergenteException();
        return repository.save(entity);
    }

}
