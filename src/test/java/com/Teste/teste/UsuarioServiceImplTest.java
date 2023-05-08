package com.Teste.teste;

import com.Teste.teste.Dominio.Usuario;
import com.Teste.teste.Dominio.dto.UsuarioDto;
import com.Teste.teste.Service.exceptions.ObjetoNaoEncontrado;
import com.Teste.teste.Service.impl.UsuarioServiceImpl;
import com.Teste.teste.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
class UsuarioServiceImplTest {

    private static final Integer ID = 3;
    private static final String NAME = "ruan";
    private static final String EMAIL = "ruan@gamil.com";
    private static final String PASSWORD = "123";

     //criando uma instância da classe impl,porém os demais mocks ele cria de "mentira"
     @InjectMocks
     private UsuarioServiceImpl service;

     //para não dá um erro de nullpointexception ,porque existe uma chamada de um metodo a classe repository
      @Mock  //não preciso ter uma uma integração com o banco para usar o objeto para o teste
      private UsuarioRepository repository;

      @Mock
      private ModelMapper mapper;

     private Usuario usuario;
     private Optional<Usuario> optionalUsuario;
     private UsuarioDto usuarioDto;


     //antes de tudo
     @BeforeEach
    void setUp(){
         //inicia os mock's(objetos de teste da classe),da classe passada
         MockitoAnnotations.openMocks(this); // o this faz referencia a própria classe testada
         startUsuario();
    }

    @Test //quando o id é encontrado e retorna a instancia do objeto
    void whenFindByIdThenReturnAndUsuarioInstance(){

         //quando findbyid for chamado com qualquer numero inteiro,então retorne um objeto do tipo Optional
         when(repository.findById(anyInt())).thenReturn(optionalUsuario);

         //passando o "ID = 3" para o service mandar uma requisição para o banco de dados buscar esse ID
         Usuario response = service.findById(ID);

         assertNotNull(response);
         //Asseguro que o objeto encontrado no banco é tipo USUARIO e não USUARIODTO
         //por opção de segurança
        assertEquals(Usuario.class,response.getClass());
        assertEquals(ID,response.getId());
        assertEquals(NAME,response.getNome());
        assertEquals(EMAIL,response.getEmail());

    }

    @Test
    void whenFindByIdThenRetornoAnObjetoNaoEncontradoException(){
         //quando passado qualquer id ,e ele não for encontrado , chama Obj não encontrado
         when(repository.findById(anyInt())).thenThrow(new ObjetoNaoEncontrado("Objeto não encontrado"));

         try {
             service.findById(ID);
         }catch (Exception ex){
             //mockando a resposta
             assertEquals(ObjetoNaoEncontrado.class,ex.getClass());

         }
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
         optionalUsuario = Optional.of(new Usuario(ID,NAME,EMAIL,PASSWORD));
  }
}
