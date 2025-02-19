package br.com.formcliente.backend.presentation.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class ClienteResponse implements Serializable {

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

    public ClienteResponse() {
    }

    public ClienteResponse(Long id, String nome, String cpf, String email, String cor, String observacao) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.cor = cor;
        this.observacao = observacao;
    }
}
