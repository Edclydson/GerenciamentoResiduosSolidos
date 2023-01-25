package com.projeto.grs.services.interfaces.citizen;

import com.projeto.grs.dto.CidadaoDTO;
import com.projeto.grs.modelo.Cidadao;
import com.projeto.grs.modelo.Pontuacao;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.Optional;

public interface ICitizenActions{


    ResponseEntity resgastar(String cpf, String referencia);

    Optional<Pontuacao> pontosDoCidadao(String id);

    void atualizaPontosCidadao(String cpf,Double pontosSaque);
}
