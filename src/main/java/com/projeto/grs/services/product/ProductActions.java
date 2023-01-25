package com.projeto.grs.services.product;

import com.projeto.grs.modelo.Produto;
import com.projeto.grs.repository.ProdutoRepository;
import com.projeto.grs.services.interfaces.product.IProductActions;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductActions implements IProductActions{

    private final ProdutoRepository repository;
    private final ProductService productService;
    private final ProductVerifications productVerifications;

    public ProductActions(ProdutoRepository repository, ProductService productService, ProductVerifications productVerifications) {
        this.repository = repository;
        this.productService = productService;
        this.productVerifications = productVerifications;
    }

    @Override
    public boolean resgateUmProduto(String referencia) {
        //MELHORAR METODO
        // 1 - VERIFICAR SE PRODUTO EXISTE
        // 2 - VERIFICAR SE PRODUTO NÃO ESTÁ COM ESTOQUE ZERADO
        // 3 - CASO EXISTA E NÃO TENHA ESTOQUE ZERO, REALIZAR SAQUE
        // 4 - VERIFICAR SE AINDA POSSUI ESTOQUE
        // 5 - CASO NÃO POSSUA, TORNAR PRODUTO INDISPONIVEL
        Optional<Produto> produtoResgatado = productService.consultarProduto(referencia);
        if(produtoResgatado.isPresent() && productVerifications.disponibilidadeProduto(produtoResgatado.get())){
            produtoResgatado.get().setQuantidadeProduto(produtoResgatado.get().getQuantidadeProduto() - 1);
             if(productVerifications.verificaProdutoZerado(produtoResgatado.get())) {
                 produtoIndisponivel(produtoResgatado.get());
             }
            return true;
        }
        return false;
    }

    @Override
    public void produtoIndisponivel(Produto produto) {
        produto.setDisponivel(false);
        repository.save(produto);
    }
}
