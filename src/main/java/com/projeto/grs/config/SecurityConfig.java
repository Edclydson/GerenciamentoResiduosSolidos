package com.projeto.grs.config;

import com.projeto.grs.repository.CidadaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class SecurityConfig{

    @Autowired
    userDetailsServiceM userDetailsServiceM;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{
        return http
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers(HttpMethod.GET,"/dashboard/**").permitAll()
                .antMatchers(HttpMethod.GET,"/produtos/**").hasRole("USER")
                .antMatchers(HttpMethod.GET,"/cidadoes/**").hasRole("ADMIN")
                .anyRequest().authenticated().and()
                //.antMatchers(HttpMethod.GET,"/dashboard/**").hasRole("USER")
                .httpBasic(Customizer.withDefaults())
                .userDetailsService(userDetailsServiceM)
                .build();

    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
