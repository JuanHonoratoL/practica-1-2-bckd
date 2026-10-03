package com.upiiz.practica1_2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.upiiz.practica1_2.heroes.application.HeroeService;
import com.upiiz.practica1_2.heroes.domain.ports.in.HeroeUseCase;
import com.upiiz.practica1_2.heroes.domain.ports.out.HeroeRepository;

@Configuration 
public class AppConfig {
    @Bean
    public HeroeUseCase heroeUseCase(HeroeRepository heroeRepository){
        return new HeroeService(heroeRepository);
    }
}
