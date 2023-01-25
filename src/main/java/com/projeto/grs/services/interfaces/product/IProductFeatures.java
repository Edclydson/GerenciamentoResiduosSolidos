package com.projeto.grs.services.interfaces.product;

import com.projeto.grs.dto.ProdutoDTO;
import com.projeto.grs.modelo.Produto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IProductFeatures{




    Produto dtoToProduct(ProdutoDTO dto);
}
