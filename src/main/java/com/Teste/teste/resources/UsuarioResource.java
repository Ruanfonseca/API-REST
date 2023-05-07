package com.Teste.teste.resources;


import com.Teste.teste.Dominio.Usuario;
import com.Teste.teste.Dominio.dto.UsuarioDto;
import com.Teste.teste.Service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
      @PostMapping
      public ResponseEntity<UsuarioDto> create(@RequestBody UsuarioDto obj){

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(service.create(obj).getId()).toUri();

        //retorna a resposta 201
        return ResponseEntity.created(uri).build();

      }
      @PutMapping(value = "/{id}")
      public ResponseEntity<UsuarioDto>update(@PathVariable Integer id , @RequestBody UsuarioDto obj){
        obj.setId(id);
        Usuario newObj = service.update(obj);
        return ResponseEntity.ok().body(mapper.map(newObj,UsuarioDto.class));
      }


      @DeleteMapping(value = "/ {id}")
      public ResponseEntity <UsuarioDto> delete(@PathVariable Integer id){

           service.delete(id);

           return ResponseEntity.noContent().build();
      }


}
