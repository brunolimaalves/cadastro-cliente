package br.com.formcliente.backend.presentation.request;

import br.com.formcliente.backend.infra.persistence.ClienteEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClienteRequest implements Serializable {

    @JsonProperty
    private Long id;
    @JsonProperty
    private String nome;
    @JsonProperty
    private String cpf;
    @JsonProperty
    private String email;
    @JsonProperty
    private String cor;
    @JsonProperty
    private String observacao;

    public ClienteEntity toEntity() {
        return new ClienteEntity(id, nome, cpf, email, cor, observacao);
    }
}
