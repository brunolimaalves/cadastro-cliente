package br.com.formcliente.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente implements Serializable {

    private Long id;

    private String nome;

    private String cpf;

    private String email;

    private String cor;

    private String observacao;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }
}
