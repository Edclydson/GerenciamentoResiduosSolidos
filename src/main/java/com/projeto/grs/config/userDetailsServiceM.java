package com.projeto.grs.config;

import com.projeto.grs.modelo.Cidadao;
import com.projeto.grs.repository.CidadaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class userDetailsServiceM implements UserDetailsService{

    @Autowired
    CidadaoRepository cidadaoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Cidadao cidadaoAchado = cidadaoRepository.findByCpf(username)
                .orElseThrow(()->new UsernameNotFoundException("Usuário não encontrado!"));
        return cidadaoAchado;
    }
}
