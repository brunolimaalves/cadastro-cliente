package br.com.formcliente.backend.infra.persistence;

import br.com.formcliente.backend.presentation.response.ClienteResponse;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity
@Table(name = "tb_clientes")
public class ClienteEntity  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cpf;

    private String email;

    private String cor;

    private String observacao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public ClienteResponse toResponse() {
        return new ClienteResponse(id, nome, cpf, email, cor, observacao);
    }

    public ClienteEntity() {
    }

    public ClienteEntity(Long id, String nome, String cpf, String email, String cor, String observacao) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.cor = cor;
        this.observacao = observacao;
    }

    public ClienteEntity(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }
}
