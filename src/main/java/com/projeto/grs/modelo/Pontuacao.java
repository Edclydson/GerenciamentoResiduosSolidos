package com.projeto.grs.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "pontuacao")
public class Pontuacao{

    @Id
    @Column(length = 11)
    private String idPontos;

    private Double pontos;
}
