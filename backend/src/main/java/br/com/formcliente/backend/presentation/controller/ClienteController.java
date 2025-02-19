package br.com.formcliente.backend.presentation.controller;

import br.com.formcliente.backend.application.usecases.AtualizarClienteUseCase;
import br.com.formcliente.backend.application.usecases.CadastrarClienteUseCase;
import br.com.formcliente.backend.application.usecases.DeletarClienteUseCase;
import br.com.formcliente.backend.application.usecases.ListarClientesFilterUseCase;
import br.com.formcliente.backend.infra.persistence.ClienteEntity;
import br.com.formcliente.backend.presentation.request.ClienteRequest;
import br.com.formcliente.backend.presentation.response.ClienteResponse;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ListarClientesFilterUseCase listarClientesFilterUseCase;
    private final CadastrarClienteUseCase cadastrarClienteUseCase;
    private final AtualizarClienteUseCase atualizarClienteUseCase;
    private final DeletarClienteUseCase deletarClienteUseCase;

    public ClienteController(ListarClientesFilterUseCase listarClientesFilterUseCase,
                             CadastrarClienteUseCase cadastrarClienteUseCase,
                             AtualizarClienteUseCase atualizarClienteUseCase,
                             DeletarClienteUseCase deletarClienteUseCase) {
        this.listarClientesFilterUseCase = listarClientesFilterUseCase;
        this.cadastrarClienteUseCase = cadastrarClienteUseCase;
        this.atualizarClienteUseCase = atualizarClienteUseCase;
        this.deletarClienteUseCase = deletarClienteUseCase;
    }

    @GetMapping("")
    public ResponseEntity<Page<ClienteResponse>> findByExample(@ModelAttribute ClienteRequest clienteRequest, Pageable pageable) {
        Example<ClienteEntity> example = Example.of(clienteRequest.toEntity());
        Page<ClienteEntity> clientes = listarClientesFilterUseCase.executa(example.getProbe(), pageable);

        if(CollectionUtils.isEmpty(clientes.getContent()))
            return ResponseEntity.noContent().build();

        List<ClienteResponse> response = clientes.stream()
                .map(ClienteEntity::toResponse)
                .toList();

        return ResponseEntity.ok(new PageImpl<>(response, pageable, clientes.getTotalElements()));
    }

    @PostMapping
    public ResponseEntity<ClienteResponse> create(@RequestBody ClienteRequest clienteRequest) {
        ClienteEntity clienteEntity = cadastrarClienteUseCase.executa(clienteRequest.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteEntity.toResponse());
    }

    @PutMapping("{clienteId}")
    public ResponseEntity<ClienteResponse> update(@PathVariable Long clienteId,
                                                  @RequestBody ClienteRequest clienteRequest) {
        ClienteEntity clienteEntity = atualizarClienteUseCase.executa(clienteId, clienteRequest.toEntity());
        return ResponseEntity.ok().body(clienteEntity.toResponse());
    }

    @DeleteMapping("{clienteId}")
    public ResponseEntity<Void> update(@PathVariable Long clienteId) {
        deletarClienteUseCase.executa(clienteId);
        return ResponseEntity.ok().build();
    }

}
