package com.crud.java.application.mapper;

import com.crud.java.application.model.Usuario;
import com.crud.java.application.model.entity.UsuarioEntity;
import org.springframework.stereotype.Service;

@Service
public class UsuarioMapperConverter {

    public UsuarioEntity conversorEntidade(Usuario usuario) {

        UsuarioEntity entidade = UsuarioEntity.builder()
                .email(usuario.getEmail()) //Insere o valor da requisição no campo da entidade
                .id(usuario.getId())
                .nome(usuario.getNome())
                .cpfCnpj("1123")
                .idade(usuario.getIdade())
                .build();

        return entidade;
    }
}
