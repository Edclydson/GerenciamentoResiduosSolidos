package com.projeto.grs.repository;

import com.projeto.grs.modelo.Produto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository extends CrudRepository<Produto, Long>{
    @Override
    Optional<Produto> findById(Long s);


}
