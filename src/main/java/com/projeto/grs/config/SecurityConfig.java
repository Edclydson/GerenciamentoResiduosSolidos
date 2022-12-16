package com.projeto.grs.config;

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

    final userDetailsServiceM userDetailsServiceM;

    public SecurityConfig(userDetailsServiceM userDetailsServiceM) {
        this.userDetailsServiceM = userDetailsServiceM;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{
        return http
                .csrf().disable().httpBasic(Customizer.withDefaults()).userDetailsService(userDetailsServiceM)
                .authorizeHttpRequests(autorizacao -> autorizacao
                        .antMatchers("/dashboard/**").permitAll()
                        .antMatchers(HttpMethod.GET,"/dashboard/produtos/**").hasRole("USER")
                        .antMatchers(HttpMethod.GET,"/dashboard/pontuacao/**").hasRole("USER"))
                .build();
    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }
}
