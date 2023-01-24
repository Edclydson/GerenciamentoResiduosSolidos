package com.projeto.grs.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


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
