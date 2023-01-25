package com.projeto.grs.controllers;

import com.projeto.grs.dto.CidadaoDTO;
import com.projeto.grs.dto.ProdutoDTO;
import com.projeto.grs.modelo.Produto;
import com.projeto.grs.services.citizen.CitizenActions;
import com.projeto.grs.services.citizen.CitizenService;
import com.projeto.grs.services.product.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class CidadaoController{
    final ProductService productService;
    final CitizenActions citizenActions;
    final CitizenService citizenService;

    public CidadaoController(ProductService productService,
                             CitizenActions citizenActions,
                             CitizenService citizenService) {
        this.productService = productService;
        this.citizenActions = citizenActions;
        this.citizenService = citizenService;
    }


    @GetMapping("/produtos")
    public ResponseEntity<List<Produto>> listarProdutos(){
       return productService.listar();
    }

    //ENDPOINT SERÁ REALOCADO PARA OUTRA API
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/produto/novo")
    public void novoProduto(@RequestBody ProdutoDTO novoProduto){
        productService.novoProduto(novoProduto);
    }
    
    @PutMapping("/resgatar/{cpf}/{ref}")
    public ResponseEntity resgateProduto(@PathVariable("cpf") String cpf, @PathVariable("ref") String referenciaProduto){
        return citizenActions.resgastar(cpf,referenciaProduto);
    }

    @GetMapping("/pontuacao/{id}")
    public ResponseEntity pontosDoCidadao(@PathVariable("id") String id){
        if(citizenActions.pontosDoCidadao(id).isPresent()){
            return ResponseEntity.ok(citizenActions.pontosDoCidadao(id));
        }
        return ResponseEntity.ok("Cidadão não consta na base de dados!");
    }

    @PostMapping("/cadastro")
    public ResponseEntity novoCidadao(@RequestBody @Valid CidadaoDTO dto, UriComponentsBuilder uriBuilder){
        var uri = uriBuilder.path("/cadastro").build(dto);
        return citizenService.cadastro(dto,uri);
    }

}
