package com.projeto.grs.services.citizen;

import com.projeto.grs.dto.CidadaoDTO;
import com.projeto.grs.modelo.Cidadao;
import com.projeto.grs.repository.CidadaoRepository;
import com.projeto.grs.services.interfaces.citizen.ICitizenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class CitizenService implements ICitizenService, UserDetailsService{

    private final CidadaoRepository repository;

    private final CitizenFeatures citizenFeatures;

    public CitizenService(CidadaoRepository repository, CitizenFeatures citizenFeatures) {
        this.repository = repository;
        this.citizenFeatures = citizenFeatures;
    }

    @Override
    public ResponseEntity cadastro(CidadaoDTO dto, URI uri) {
        repository.save(citizenFeatures.DtoToCitizen(dto));
        return ResponseEntity.created(uri).build();
    }

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        Cidadao citizen = repository.findByCpf(cpf)
                .orElseThrow(() -> new UsernameNotFoundException("Cidadão não encontrado!"));
        return citizen;
    }
}
