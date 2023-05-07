package com.Teste.teste.resources.exceptions;

import com.Teste.teste.Service.exceptions.ObjetoNaoEncontrado;
import com.Teste.teste.Service.exceptions.ViolacaoDedadosIntegradosException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public class ManipuladorDeExcecao {

    @ExceptionHandler(ObjetoNaoEncontrado.class)
    public ResponseEntity<ErroPadrao>ObjetoNaoEncontrado(ObjetoNaoEncontrado ex, HttpServletRequest request){
     ErroPadrao error = new ErroPadrao(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),ex.getMessage(),request.getRequestURI());
     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ViolacaoDedadosIntegradosException.class)
    public ResponseEntity<ErroPadrao>ViolacaoDedadosIntegradosException(ViolacaoDedadosIntegradosException ex, HttpServletRequest request){
        ErroPadrao error = new ErroPadrao(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(),ex.getMessage(),request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
