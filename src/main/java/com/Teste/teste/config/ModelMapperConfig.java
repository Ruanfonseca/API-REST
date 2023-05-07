package com.Teste.teste.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean // criando uma injeção de model mapper
    public ModelMapper mapper(){
         return new ModelMapper();
    }


}
