package com.projeto.grs.dto;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@Setter
public class CidadaoDTO{
    @Override
    public String toString() {
        return "CidadaoDTO{" +
                "nome='" + nome + '\'' +
                ", idade='" + idade + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }

    private String nome;
    private String idade;
    @CPF
    private String cpf;
    private String email;
    private String senha;
    private String telefone;
    private String endereco;
}
