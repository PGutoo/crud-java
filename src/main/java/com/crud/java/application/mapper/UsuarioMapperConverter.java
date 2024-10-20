package com.crud.java.application.mapper;

import com.crud.java.application.model.dto.UsuarioDTO;
import com.crud.java.application.model.entity.UsuarioEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsuarioMapperConverter {

    public UsuarioEntity conversorEntidade(UsuarioDTO usuario) {

        UsuarioEntity entidade = UsuarioEntity.builder()
                .email(usuario.getEmail()) //Insere o valor da requisição no campo da entidade
                .id(UUID.randomUUID().toString())
                .nome(usuario.getNome())
                .cpfCnpj("1123")
                .idade(usuario.getIdade())
                .build();

        return entidade;
    }
}
