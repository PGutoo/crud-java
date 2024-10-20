package com.crud.java.application.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UsuarioDTO {

    private String id;
    private String nome;
    private String cpfCnpj;
    private String email;
    private String idade;

}
