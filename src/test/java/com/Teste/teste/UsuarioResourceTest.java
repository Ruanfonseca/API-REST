package com.Teste.teste;

import com.Teste.teste.Dominio.Usuario;
import com.Teste.teste.Dominio.dto.UsuarioDto;
import com.Teste.teste.Service.UsuarioService;
import com.Teste.teste.Service.impl.UsuarioServiceImpl;
import com.Teste.teste.resources.UsuarioResource;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UsuarioResourceTest {

    private static final Integer ID = 3;
    private static final String NAME = "ruan";
    private static final String EMAIL = "ruan@gamil.com";
    private static final String PASSWORD = "123";
    Integer INDEX = 0;

    private Usuario usuario;
    private UsuarioDto usuarioDto;

    @InjectMocks
    private UsuarioResource resource;
    @Mock
    private UsuarioServiceImpl service;
    @Mock
    private ModelMapper mapper;

 @BeforeEach
 void setUp(){
     MockitoAnnotations.openMocks(this);
     startUsuario();
 }

@Test
void  whenFindByIdThenReturnSuccess(){

    when(service.findById(Mockito.anyInt())).thenReturn(usuario);

    /*Qualquer classe que ele tentar converter de usuario para usuarioDto */
    when(mapper.map(any(),any())).thenReturn(usuarioDto);

    ResponseEntity<UsuarioDto> response = resource.findById(ID);

    //Validações
    assertNotNull(response);
    assertNotNull(response.getBody());//tem que vir um usuario DTO
    assertEquals(ResponseEntity.class,response.getClass());
    //assegura que o objeto do corpo do response é um DTO
    assertEquals(UsuarioDto.class,response.getBody().getClass());
    assertEquals(ID,response.getBody().getId());
    assertEquals(NAME,response.getBody().getNome());
    assertEquals(EMAIL,response.getBody().getEmail());
    assertEquals(PASSWORD,response.getBody().getSenha());


 }



 @Test
 void whenFindAllThenReturnaSlistadeUsuarioDTO() {
     when(service.findALL()).thenReturn(List.of(usuario));
     when(mapper.map(any(),any())).thenReturn(usuarioDto);
     ResponseEntity<List<UsuarioDto>> response = resource.findAll();
     assertNotNull(response);
     assertNotNull(response.getBody());
     assertEquals(ResponseEntity.class,response.getClass());
     assertEquals(ArrayList.class,response.getBody().getClass());
     assertEquals(UsuarioDto.class,response.getBody().get(INDEX).getClass());

     assertEquals(ID,response.getBody().get(INDEX).getId());
     assertEquals(NAME,response.getBody().get(INDEX).getNome());
     assertEquals(EMAIL,response.getBody().get(INDEX).getEmail());
     assertEquals(PASSWORD,response.getBody().get(INDEX).getSenha());
 }
 @Test
 void whenCreateThenRetornoCriacao(){
       when(service.create(any())).thenReturn(usuario);

       ResponseEntity<UsuarioDto> response = resource.create(usuarioDto);

       assertEquals(ResponseEntity.class,response.getClass());
       assertEquals(HttpStatus.CREATED,response.getStatusCode());
       assertNotNull(response.getHeaders().get("Location"));
 }
 @Test
 void whenUpdateThenRetornoComSucesso(){
     when(service.update(usuarioDto)).thenReturn(usuario);
     when(mapper.map(any(),any())).thenReturn(usuarioDto);

     ResponseEntity<UsuarioDto> response = resource.update(ID,usuarioDto);
     assertNotNull(response);
     assertNotNull(response.getBody());
     assertEquals(HttpStatus.OK,response.getStatusCode());
     assertEquals(ResponseEntity.class,response.getClass());
     assertEquals(UsuarioDto.class,response.getBody().getClass());
     assertEquals(ID,response.getBody().getId());
     assertEquals(NAME,response.getBody().getNome());
     assertEquals(EMAIL,response.getBody().getEmail());

 }
  @Test
  void delete(){

  }

    private void startUsuario(){

        usuario = new Usuario(ID,NAME,EMAIL,PASSWORD);
        usuarioDto = new UsuarioDto(ID,NAME,EMAIL,PASSWORD);

    }

}
