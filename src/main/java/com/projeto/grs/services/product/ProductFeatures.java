package com.projeto.grs.services.product;

import com.projeto.grs.dto.ProdutoDTO;
import com.projeto.grs.modelo.Produto;
import com.projeto.grs.services.interfaces.product.IProductFeatures;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ProductFeatures implements IProductFeatures{


    @Override
    public Produto dtoToProduct(ProdutoDTO dto) {
        Produto novo = new Produto();
        novo.setIdProduto(new Date().getTime());
        novo.setNomeProduto(dto.getNomeProduto());
        novo.setQuantidadeProduto(dto.getQuantidadeProduto());
        novo.setValorPontos(dto.getValorPontos());
        novo.setDisponivel(true);
        return novo;
    }
}
