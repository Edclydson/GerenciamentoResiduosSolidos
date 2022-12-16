package com.projeto.grs.modelo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "produtos")
public class Produto{
    @Id
    private Long idProduto;

    private Double valorPontos;
    private String nomeProduto;
    private int quantidadeProduto;
    private boolean disponivel;
}
