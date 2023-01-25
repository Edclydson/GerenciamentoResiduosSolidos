package com.projeto.grs.services.citizen;

import com.projeto.grs.modelo.Pontuacao;
import com.projeto.grs.modelo.Produto;
import com.projeto.grs.repository.PontuacaoRepository;
import com.projeto.grs.services.interfaces.citizen.ICitizenVerifications;
import org.springframework.stereotype.Service;

@Service
public class CitizenVerifications implements ICitizenVerifications{

    private final PontuacaoRepository repository;

    public CitizenVerifications(PontuacaoRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean verificaPontosCidadao(String cpf) {
        Pontuacao pontosCidadao = repository.findById(cpf).get();
        return pontosCidadao.getPontos() > 0;
    }

    @Override
    public boolean verificaPontosParaSaque(Pontuacao pontosCidadao, Produto produtoAsacar) {
        return pontosCidadao.getPontos() >= produtoAsacar.getValorPontos();
    }
}
