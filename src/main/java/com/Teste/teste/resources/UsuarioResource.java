package com.Teste.teste.resources;


import com.Teste.teste.Dominio.Usuario;
import com.Teste.teste.Dominio.dto.UsuarioDto;
import com.Teste.teste.Service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/usuario")
public class UsuarioResource {

   @Autowired
   private ModelMapper mapper;

    @Autowired
    private UsuarioService service;



    @GetMapping(value="/{id}")
    public ResponseEntity<UsuarioDto> findById(@PathVariable Integer id){
         return ResponseEntity.ok().body(mapper.map(service.findById(id), UsuarioDto.class));
     }


      @GetMapping
      public ResponseEntity<List<UsuarioDto>> findAll(){
          List<UsuarioDto> listDTO = service.findALL().stream()
                  .map(x->mapper.map(x, UsuarioDto.class)).collect(Collectors.toList());
          return ResponseEntity.ok().body(listDTO);
      }


}
