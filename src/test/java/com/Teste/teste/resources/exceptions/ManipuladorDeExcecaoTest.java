package com.Teste.teste.resources.exceptions;

import com.Teste.teste.Service.exceptions.ObjetoNaoEncontrado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ManipuladorDeExcecaoTest extends com.Teste.teste.ManipuladorDeExcecaoTest {

    @InjectMocks
    private ManipuladorDeExcecao manipuladorDeExcecao;

    @BeforeEach
    void setUp() {
     MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenObjetoNaoEncontradoThenRetornaUmResponseEntity() {

        ResponseEntity<ErroPadrao> response = manipuladorDeExcecao
                .ObjetoNaoEncontrado(new ObjetoNaoEncontrado("Objeto não encontrado"),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
        assertEquals(ResponseEntity.class,response.getClass());
        assertEquals(ErroPadrao.class,response.getBody().getClass());
        assertEquals("Objeto não encontrado",response.getBody().getError());
        assertEquals(404,response.getBody().getStatus());

    }

    @Test
    void violacaoDedadosIntegradosException() {
    }
}