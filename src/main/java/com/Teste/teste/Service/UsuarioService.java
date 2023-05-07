package com.Teste.teste.Service;

import com.Teste.teste.Dominio.Usuario;
import com.Teste.teste.Dominio.dto.UsuarioDto;

import java.util.List;

public interface UsuarioService {

 Usuario findById(Integer id);
  List<Usuario> findALL();
  Usuario create(UsuarioDto obj);

  Usuario update(UsuarioDto obj);

}
