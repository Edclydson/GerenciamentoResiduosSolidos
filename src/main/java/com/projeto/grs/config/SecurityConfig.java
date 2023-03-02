package com.projeto.grs.config;

import com.projeto.grs.services.citizen.CitizenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig{

    private final CitizenService citizenService;

    public SecurityConfig(CitizenService citizenService) {
        this.citizenService = citizenService;
    }

    @Bean
    SecurityFilterChain config(HttpSecurity http) throws Exception {
        return http
//                .userDetailsService(citizenService)
                .csrf(csfr -> csfr.disable()) // desativado somente por ser ambiente de desenvolvimento
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests()
//                .requestMatchers(HttpMethod.POST,"/dashboard/cadastro").permitAll()
//                .requestMatchers("/swagger-ui/**").permitAll()
//                .requestMatchers("/v3/api-docs/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .build();
    }

    @Bean
    BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
