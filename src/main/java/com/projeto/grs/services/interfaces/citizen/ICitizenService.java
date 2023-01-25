package com.projeto.grs.services.interfaces.citizen;

import com.projeto.grs.dto.CidadaoDTO;
import org.springframework.http.ResponseEntity;

import java.net.URI;

public interface ICitizenService{

    ResponseEntity cadastro(CidadaoDTO dto, URI uri);
}
