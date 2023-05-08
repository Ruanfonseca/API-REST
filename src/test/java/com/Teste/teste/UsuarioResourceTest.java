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
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UsuarioResourceTest {

    private static final Integer ID = 3;
    private static final String NAME = "ruan";
    private static final String EMAIL = "ruan@gamil.com";
    private static final String PASSWORD = "123";

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
    Assertions.assertNotNull(response);
    Assertions.assertNotNull(response.getBody());//tem que vir um usuario DTO
    Assertions.assertEquals(ResponseEntity.class,response.getClass());
    //assegura que o objeto do corpo do response é um DTO
    Assertions.assertEquals(UsuarioDto.class,response.getBody().getClass());
    Assertions.assertEquals(ID,response.getBody().getId());
    Assertions.assertEquals(NAME,response.getBody().getNome());
    Assertions.assertEquals(EMAIL,response.getBody().getEmail());
    Assertions.assertEquals(PASSWORD,response.getBody().getSenha());


 }
 @Test
 void findAll(){

 }
 @Test
 void create(){

 }
 @Test
 void update(){

 }
  @Test
  void delete(){

  }

    private void startUsuario(){

        usuario = new Usuario(ID,NAME,EMAIL,PASSWORD);
        usuarioDto = new UsuarioDto(ID,NAME,EMAIL,PASSWORD);

    }















}
