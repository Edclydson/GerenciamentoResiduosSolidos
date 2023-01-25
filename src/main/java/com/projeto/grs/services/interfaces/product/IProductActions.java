package com.projeto.grs.services.interfaces.product;

import com.projeto.grs.modelo.Produto;

public interface IProductActions{

    boolean resgateUmProduto(String referencia);

    void produtoIndisponivel(Produto produto);
}
