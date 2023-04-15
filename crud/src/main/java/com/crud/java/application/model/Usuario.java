package com.crud.java.application.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Builder
@Getter
@Setter
public class Usuario {

    @JsonProperty("id")
    private String id;

    @JsonProperty("nome")
    private String nome;

    @JsonProperty("cpf_cnpj")
    private String cpfCnpj;

    @JsonProperty("email")
    private String email;

    @JsonProperty("idade")
    private String idade;

}
