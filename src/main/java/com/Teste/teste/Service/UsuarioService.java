package com.Teste.teste.Service;

import com.Teste.teste.Dominio.Usuario;

import java.util.List;

public interface UsuarioService {

 Usuario findById(Integer id);
  List<Usuario> findALL();
}
