package com.projeto.grs.controllers;

import com.projeto.grs.dto.CidadaoDTO;
import com.projeto.grs.modelo.Cidadao;
import com.projeto.grs.modelo.Produto;
import com.projeto.grs.services.CidadaoService;
import com.projeto.grs.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class CidadaoController{
    @Autowired
    ProdutoService produtoService;

    @Autowired
    CidadaoService cidadaoService;

    @PreAuthorize(value = "hasRole('USER')")
    @GetMapping("/produtos")
    public List<Produto> listarProdutos(){
       return produtoService.listar();
    }

    //TEMPORARIO
    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping("/cidadoes")
    public List<Cidadao> listarCidadaos(){
        return cidadaoService.listarCidadaos();
    }

    @PostMapping("/cadastro")
    public void novoCidadao(@RequestBody CidadaoDTO dto){
        cidadaoService.cadastro(dto);
    }

}
