package com.Teste.teste.resources;


import com.Teste.teste.Dominio.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/usuario")
public class UsuarioResource {

    @GetMapping(value="/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Integer id){
      return ResponseEntity.ok().body(new Usuario(1,"ruan","ruan@gmail.com","123"));
     }

}
