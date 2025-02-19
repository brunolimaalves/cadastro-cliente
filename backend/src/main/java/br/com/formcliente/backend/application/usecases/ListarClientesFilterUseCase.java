package br.com.formcliente.backend.application.usecases;

import br.com.formcliente.backend.domain.repository.ClienteRepository;
import br.com.formcliente.backend.infra.persistence.ClienteEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class ListarClientesFilterUseCase {

    private final ClienteRepository repository;

    public ListarClientesFilterUseCase(ClienteRepository repository) {
        this.repository = repository;
    }

    public Page<ClienteEntity> executa(ClienteEntity entity, Pageable pageable) {
        Example<ClienteEntity> example = Example.of( entity );
        return repository.findAll(example, pageable);
    }

}
