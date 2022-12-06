package com.projeto.grs.repository;

import com.projeto.grs.modelo.Pontuacao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PontuacaoRepository extends CrudRepository <Pontuacao, String>{
}
