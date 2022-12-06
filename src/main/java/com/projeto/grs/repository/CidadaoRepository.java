package com.projeto.grs.repository;

import com.projeto.grs.modelo.Cidadao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CidadaoRepository extends CrudRepository<Cidadao, String>{

    List<Cidadao> findAll();


    Optional<Cidadao> findByCpf(String cpf);


}
