package br.com.formcliente.backend.application.usecases;

import br.com.formcliente.backend.application.exception.ClienteJaExisteException;
import br.com.formcliente.backend.domain.repository.ClienteRepository;
import br.com.formcliente.backend.infra.persistence.ClienteEntity;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CadastrarClienteUseCase {

    private final ClienteRepository repository;

    public CadastrarClienteUseCase(ClienteRepository repository) {
        this.repository = repository;
    }

    public ClienteEntity executa(ClienteEntity entity) {
        Optional<ClienteEntity> clienteDB = repository.findByCpf(entity.getCpf());
        if(clienteDB.isPresent()) {
            throw new ClienteJaExisteException();
        }
        return repository.save(entity);
    }

}
