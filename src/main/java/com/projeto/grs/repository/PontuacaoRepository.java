package com.projeto.grs.repository;

import com.projeto.grs.modelo.Pontuacao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PontuacaoRepository extends CrudRepository <Pontuacao, String>{
    Optional<Pontuacao> findById(String cpf);
}
