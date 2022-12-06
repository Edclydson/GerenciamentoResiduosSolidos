package com.projeto.grs.modelo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

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
