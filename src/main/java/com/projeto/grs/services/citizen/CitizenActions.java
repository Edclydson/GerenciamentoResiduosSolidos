package com.projeto.grs.services.citizen;

import com.projeto.grs.modelo.Pontuacao;
import com.projeto.grs.modelo.Produto;
import com.projeto.grs.repository.PontuacaoRepository;
import com.projeto.grs.services.interfaces.citizen.ICitizenActions;
import com.projeto.grs.services.product.ProductActions;
import com.projeto.grs.services.product.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CitizenActions implements ICitizenActions{

    private final PontuacaoRepository repository;
    private final ProductService productService;
    private final ProductActions productActions;
    private final CitizenVerifications citizenVerifications;
    public CitizenActions(PontuacaoRepository repository,
                          ProductService productService,
                          ProductActions productActions,
                          CitizenVerifications citizenVerifications) {
        this.repository = repository;
        this.productService = productService;
        this.productActions = productActions;
        this.citizenVerifications = citizenVerifications;
    }

    @Override
    public ResponseEntity resgastar(String cpf, String referencia) {
        Pontuacao pontuacaoCidadao = pontosDoCidadao(cpf).get();
        Produto produtoResgatar = productService.consultarProduto(referencia).get();
        if((citizenVerifications.verificaPontosCidadao(cpf) &&
                citizenVerifications.verificaPontosParaSaque(pontuacaoCidadao,produtoResgatar))){
            if(productActions.resgateUmProduto(produtoResgatar.getIdProduto().toString())){
                atualizaPontosCidadao(pontuacaoCidadao.getIdPontos(), produtoResgatar.getValorPontos());
                return ResponseEntity.ok("Saque realizado com sucesso!");
            }else{
                return ResponseEntity.ok("Saque não realizado! Produto indísponível");
            }
        }
        return ResponseEntity.ok("Deu ruim! Você não tem pontos suficiente para sacar este produto! :(");
    }

    @Override
    public Optional<Pontuacao> pontosDoCidadao(String id) {
        return repository.findById(id);
    }

    @Override
    public void atualizaPontosCidadao(String cpf, Double pontosSaque) {
        Pontuacao novaPontuacao = repository.findById(cpf).get();
        novaPontuacao.setPontos(novaPontuacao.getPontos() - pontosSaque);
        repository.save(novaPontuacao);
    }
}
