package com.projeto.grs.services.interfaces.product;

import com.projeto.grs.modelo.Produto;

public interface IProductVerifications{

    boolean verificaProdutoZerado(Produto produto);

    boolean disponibilidadeProduto(Produto produto);
}
