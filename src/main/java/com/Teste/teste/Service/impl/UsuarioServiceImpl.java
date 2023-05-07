package com.Teste.teste.Service.impl;

import com.Teste.teste.Dominio.Usuario;
import com.Teste.teste.Service.UsuarioService;
import com.Teste.teste.Service.exceptions.ObjetoNaoEncontrado;
import com.Teste.teste.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;


    @Override
    public Usuario findById(Integer id) {
        //se encontrar um objeto , retorne , senão , returne um obj nulo
       Optional<Usuario> obj = repository.findById(id);
       return obj.orElseThrow(()->new ObjetoNaoEncontrado("Objeto não encontrado"));
    }


    public List<Usuario> findALL(){
        return repository.findAll();
    }
}
