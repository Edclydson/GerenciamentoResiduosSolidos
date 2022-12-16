package com.projeto.grs.services;

import com.projeto.grs.dto.ProdutoDTO;
import com.projeto.grs.modelo.Produto;
import com.projeto.grs.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ProdutoService{
    @Autowired
    ProdutoRepository produtoRepository;

    public ResponseEntity<Iterable<Produto>> listar() {
        var page = produtoRepository.findAll();
        return ResponseEntity.ok(page);
    }


    public void novoProduto(ProdutoDTO novoProduto){
        Produto novo = new Produto();
        try{
            novo.setIdProduto(new Date().getTime());
            novo.setNomeProduto(novoProduto.getNomeProduto());
            novo.setQuantidadeProduto(novoProduto.getQuantidadeProduto());
            novo.setValorPontos(novoProduto.getValorPontos());
            novo.setDisponivel(true);
            produtoRepository.save(novo);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Optional<Produto> consultarProduto(String referencia) {
        return produtoRepository.findById(Long.parseLong(referencia));
    }

    public boolean resgateUmProduto(String referencia){
        Produto produtoResgatado = produtoRepository.findById(Long.parseLong(referencia)).get();
        if(disponibilidadeProduto(produtoResgatado) == true){
            try{
                produtoResgatado.setQuantidadeProduto(produtoResgatado.getQuantidadeProduto() - 1);
                verificaProdutoZerado(produtoResgatado);
                return true;
            }catch (Exception e){e.printStackTrace();}
        }
        else{produtoIndisponivel(produtoResgatado);}
        return false;
    }

    private boolean disponibilidadeProduto(Produto produto){
        if(produto.getQuantidadeProduto() >= 1){
            return true;
        }
        return false;
    }

    private void produtoIndisponivel(Produto produto){
        produto.setDisponivel(false);
        produtoRepository.save(produto);
    }

    private void verificaProdutoZerado(Produto produto){
        if(produto.getQuantidadeProduto() == 0){
            produtoIndisponivel(produto);
        }
        else{
            produtoRepository.save(produto);
        }
    }
}
