package com.projeto.grs.controllers;

import com.projeto.grs.dto.CidadaoDTO;
import com.projeto.grs.dto.ProdutoDTO;
import com.projeto.grs.modelo.Pontuacao;
import com.projeto.grs.modelo.Produto;
import com.projeto.grs.services.CidadaoService;
import com.projeto.grs.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/dashboard")
public class CidadaoController{
    @Autowired
    ProdutoService produtoService;

    @Autowired
    CidadaoService cidadaoService;


    @PreAuthorize(value = "hasRole('USER')")
    @GetMapping("/produtos")
    public ResponseEntity<Iterable<Produto>> listarProdutos(){
       return produtoService.listar();
    }

    //ENDPOINT SERÁ REALOCADO PARA OUTRA API
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/produto/novo")
    public void novoProduto(@RequestBody ProdutoDTO novoProduto){
        produtoService.novoProduto(novoProduto);
    }
    @PutMapping("/resgatar/{cpf}/{ref}")
    public ResponseEntity resgateProduto(@PathVariable("cpf") String cpf, @PathVariable("ref") String referenciaProduto){
        return cidadaoService.resgastar(cpf,referenciaProduto);
    }

    @PreAuthorize(value = "hasRole('USER')")
    @GetMapping("/pontuacao/{id}")
    public Optional<Pontuacao> pontosDoCidadao(@PathVariable("id") String id){
        return cidadaoService.pontosDoCidadao(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/cadastro")
    public void novoCidadao(@RequestBody CidadaoDTO dto){
        cidadaoService.cadastro(dto);
    }

}
