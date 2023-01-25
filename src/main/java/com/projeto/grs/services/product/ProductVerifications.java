package com.projeto.grs.services.product;

import com.projeto.grs.modelo.Produto;
import com.projeto.grs.services.interfaces.product.IProductVerifications;
import org.springframework.stereotype.Service;

@Service
public class ProductVerifications implements IProductVerifications{


    @Override
    public boolean verificaProdutoZerado(Produto produto) {
        return produto.getQuantidadeProduto() == 0;
    }

    @Override
    public boolean disponibilidadeProduto(Produto produto) {
        return produto.getQuantidadeProduto() >= 1;
    }
}
