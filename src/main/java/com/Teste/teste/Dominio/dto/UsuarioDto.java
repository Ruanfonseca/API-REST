package com.Teste.teste.Dominio.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {
    private Integer id;
    private String nome;
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // ignorando a senha quando retorna um dto com GET
    private String senha;
}
