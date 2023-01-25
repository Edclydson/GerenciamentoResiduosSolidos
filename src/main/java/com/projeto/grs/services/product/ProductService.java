package com.projeto.grs.services.product;

import com.projeto.grs.dto.ProdutoDTO;
import com.projeto.grs.modelo.Produto;
import com.projeto.grs.repository.ProdutoRepository;
import com.projeto.grs.services.interfaces.product.IProductRepositoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductRepositoryService{

    private final ProdutoRepository repository;

    private final ProductFeatures productFeatures;

    public ProductService(ProdutoRepository repository, ProductFeatures productFeatures) {
        this.repository = repository;
        this.productFeatures = productFeatures;
    }

    public ResponseEntity<List<Produto>> listar() {
        List<Produto> response = repository.findAll();
        return ResponseEntity.ok(response);
    }

    public void novoProduto(ProdutoDTO novoProduto){
        repository.save(productFeatures.dtoToProduct(novoProduto));
    }

    public Optional<Produto> consultarProduto(String referencia) throws NumberFormatException{
        return repository.findById(Long.parseLong(referencia));
    }
}
