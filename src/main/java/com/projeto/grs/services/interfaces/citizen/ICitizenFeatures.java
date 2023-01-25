package com.projeto.grs.services.interfaces.citizen;

import com.projeto.grs.dto.CidadaoDTO;
import com.projeto.grs.modelo.Cidadao;
import com.projeto.grs.modelo.Pontuacao;

public interface ICitizenFeatures{

    Pontuacao pointsToNewCitizen(String cpf);

    Cidadao DtoToCitizen(CidadaoDTO dto);
}
