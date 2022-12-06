package com.projeto.grs.services;

import com.projeto.grs.dto.CidadaoDTO;
import com.projeto.grs.modelo.Cidadao;
import com.projeto.grs.modelo.Pontuacao;
import com.projeto.grs.modelo.Roles;
import com.projeto.grs.repository.CidadaoRepository;
import com.projeto.grs.repository.PontuacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class CidadaoService{
    @Autowired
    private CidadaoRepository cidadaoRepository;

    @Autowired
    private PontuacaoRepository pontuacaoRepository;


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

    public List<Cidadao> listarCidadaos(){
        return cidadaoRepository.findAll();
    }

    private BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
