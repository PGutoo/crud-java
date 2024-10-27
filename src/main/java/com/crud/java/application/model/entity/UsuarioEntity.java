package com.crud.java.application.model.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioEntity {

    @Column(name = "NOME")
    private String nome;

    @Id
    @Column(name = "CPFCNPJ")
    private String cpfCnpj;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "IDADE")
    private String idade;
}
