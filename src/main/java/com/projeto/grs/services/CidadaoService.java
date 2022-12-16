package com.projeto.grs.services;

import com.projeto.grs.dto.CidadaoDTO;
import com.projeto.grs.modelo.Cidadao;
import com.projeto.grs.modelo.Pontuacao;
import com.projeto.grs.modelo.Produto;
import com.projeto.grs.modelo.Roles;
import com.projeto.grs.repository.CidadaoRepository;
import com.projeto.grs.repository.PontuacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.Optional;

@Service
public class CidadaoService {
    @Autowired
    private CidadaoRepository cidadaoRepository;

    @Autowired
    private PontuacaoRepository pontuacaoRepository;

    @Autowired
    private ProdutoService produtoService;


    public void cadastro(CidadaoDTO dto){
        Pontuacao pontos = new Pontuacao();
        Cidadao cidadao = new Cidadao();

        try{
            pontos.setIdPontos(dto.getCpf());
            pontos.setPontos(0.0);
            cidadao.setPontos(pontos);
            cidadao.setCpf(dto.getCpf());
            cidadao.setNome(dto.getNome());
            cidadao.setIdade(dto.getIdade());
            cidadao.setEmail(dto.getEmail());
            cidadao.setTelefone(dto.getTelefone());
            cidadao.setEndereco(dto.getEndereco());
            cidadao.setSenha(dto.getSenha());
            cidadao.setSenha(encoder().encode(cidadao.getSenha()));
            cidadao.setRole(Roles.ROLE_USER.toString());
            pontuacaoRepository.save(pontos);
            cidadaoRepository.save(cidadao);
        }
        catch (Exception e){e.printStackTrace();}
    }

    public Optional<Pontuacao> pontosDoCidadao(String id){
        return pontuacaoRepository.findById(id);
    }

    public ResponseEntity resgastar(String cpf, String referencia){
        Pontuacao pontuacaoCidadao = pontosDoCidadao(cpf).get();
        Produto produtoResgatar = produtoService.consultarProduto(referencia).get();
        if(verificaPontosCidadao(cpf) == true){
            if (verificaPontosParaSaque(pontuacaoCidadao,produtoResgatar) == true){
                try{
                    boolean processoResgateProduto = produtoService.resgateUmProduto(produtoResgatar.getIdProduto().toString());
                    if(processoResgateProduto == true){
                        atualizaPontosCidadao(pontuacaoCidadao.getIdPontos(), produtoResgatar.getValorPontos());
                        return ResponseEntity.ok("Saque realizado com sucesso!");
                    }else{
                        return ResponseEntity.ok("Saque não realizado! Produto indísponível");
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return ResponseEntity.ok("Deu ruim! Você não tem pontos suficiente para sacar este produto! :(");
    }
    private BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    private void atualizaPontosCidadao(String cpf,Double pontosSaque){
        try{
            Pontuacao novaPontuacao = pontuacaoRepository.findById(cpf).get();
            novaPontuacao.setPontos(novaPontuacao.getPontos() - pontosSaque);
            pontuacaoRepository.save(novaPontuacao);
        }catch (Exception e){e.printStackTrace();}
    }

    private boolean verificaPontosCidadao(String cpf){
        Pontuacao pontosCidadao = pontuacaoRepository.findById(cpf).get();
        if(pontosCidadao.getPontos() > 0){
            return true;
        }
        return false;
    }
    private boolean verificaPontosParaSaque(Pontuacao pontosCidadao,Produto produtoAsacar){
        if(pontosCidadao.getPontos() >= produtoAsacar.getValorPontos()){
            return true;
        }
        return false;
    }
}
