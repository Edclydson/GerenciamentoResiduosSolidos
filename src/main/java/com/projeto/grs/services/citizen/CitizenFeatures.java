package com.projeto.grs.services.citizen;

import com.projeto.grs.dto.CidadaoDTO;
import com.projeto.grs.modelo.Cidadao;
import com.projeto.grs.modelo.Pontuacao;
import com.projeto.grs.modelo.Roles;
import com.projeto.grs.repository.PontuacaoRepository;
import com.projeto.grs.services.interfaces.citizen.ICitizenFeatures;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CitizenFeatures implements ICitizenFeatures{

    private final PontuacaoRepository repository;

    public CitizenFeatures(PontuacaoRepository repository) {
        this.repository = repository;
    }

    private BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public Pontuacao pointsToNewCitizen(String cpf) {
        Pontuacao pontos = new Pontuacao();
        pontos.setIdPontos(cpf);
        pontos.setPontos(0.0);
        repository.save(pontos);
        return pontos;
    }

    @Override
    public Cidadao DtoToCitizen(CidadaoDTO dto) {
        Cidadao cidadao = new Cidadao();
        cidadao.setCpf(dto.getCpf());
        cidadao.setNome(dto.getNome());
        cidadao.setIdade(dto.getIdade());
        cidadao.setEmail(dto.getEmail());
        cidadao.setTelefone(dto.getTelefone());
        cidadao.setEndereco(dto.getEndereco());
        cidadao.setRoles(Roles.ROLE_USER.toString());
        cidadao.setSenha(encoder().encode(dto.getSenha()));
        cidadao.setPontos(pointsToNewCitizen(dto.getCpf()));
        return cidadao;
    }
}
