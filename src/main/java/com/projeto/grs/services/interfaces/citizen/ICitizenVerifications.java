package com.projeto.grs.services.interfaces.citizen;

import com.projeto.grs.modelo.Pontuacao;
import com.projeto.grs.modelo.Produto;

public interface ICitizenVerifications{

    boolean verificaPontosCidadao(String cpf);

    boolean verificaPontosParaSaque(Pontuacao pontosCidadao, Produto produtoAsacar);

}
