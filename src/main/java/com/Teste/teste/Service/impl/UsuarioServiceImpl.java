package com.Teste.teste.Service.impl;

import com.Teste.teste.Dominio.Usuario;
import com.Teste.teste.Dominio.dto.UsuarioDto;
import com.Teste.teste.Service.UsuarioService;
import com.Teste.teste.Service.exceptions.ObjetoNaoEncontrado;
import com.Teste.teste.Service.exceptions.ViolacaoDedadosIntegradosException;
import com.Teste.teste.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Usuario findById(Integer id) {
        //se encontrar um objeto , retorne , senão , returne um obj nulo
       Optional<Usuario> obj = repository.findById(id);
       return obj.orElseThrow(()->new ObjetoNaoEncontrado("Objeto não encontrado"));
    }


    public List<Usuario> findALL(){
        return repository.findAll();
    }

    @Override
    public Usuario create(UsuarioDto obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj,Usuario.class));
    }

    @Override
    public Usuario update(UsuarioDto obj) {
        findByEmail(obj);
        return repository.save(mapper.map(obj, Usuario.class));
    }

    @Override
    public void delete(Integer id) {
        //se não existir ele chama uma execeção customizada
         findById(id);
         repository.deleteById(id);
    }

    private void findByEmail(UsuarioDto obj){
        Optional<Usuario> Usuario = repository.findByEmail(obj.getEmail());
        //se o Id buscado for diferente do id que veio como parametro , significa que é um id de outro usuario
        if(Usuario.isPresent() && !Usuario.get().getId().equals(obj.getId()) ){
           throw new ViolacaoDedadosIntegradosException("Email ja cadastrado no sistema!");
        }
    }
}
