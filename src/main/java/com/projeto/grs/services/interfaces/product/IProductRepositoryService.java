package com.projeto.grs.services.interfaces.product;

import com.projeto.grs.dto.ProdutoDTO;
import com.projeto.grs.modelo.Produto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IProductRepositoryService{

    ResponseEntity<List<Produto>> listar();

    void novoProduto(ProdutoDTO novoProduto);

    Optional<Produto> consultarProduto(String referencia);
}
