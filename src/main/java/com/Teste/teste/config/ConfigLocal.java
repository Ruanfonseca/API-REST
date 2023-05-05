package com.Teste.teste.config;


import com.Teste.teste.Dominio.Usuario;
import com.Teste.teste.Service.UsuarioService;
import com.Teste.teste.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class ConfigLocal {

    @Autowired
    private UsuarioRepository repository;
    @Bean
    public void IniciaBanco(){
    Usuario u1 = new Usuario(null,"lala@gmail.com","ruan","123");
    Usuario u2 = new Usuario(null,"ruan@gmail.com","Marcelly","123");
    repository.saveAll(List.of(u1,u2));
}

}
