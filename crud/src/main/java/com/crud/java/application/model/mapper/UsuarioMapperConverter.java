package com.crud.java.application.model.mapper;

import com.crud.java.application.model.Usuario;
import com.crud.java.application.model.entity.UsuarioEntity;
import org.springframework.stereotype.Service;

@Service
public class UsuarioMapperConverter {

    public UsuarioEntity conversorEntidade(Usuario usuario) {

        UsuarioEntity entidade = UsuarioEntity.builder()
                .idade(usuario.getIdade())
                .email(usuario.getEmail())
                .id(usuario.getId())
                .nome(usuario.getNome())
                .cpfCnpj(usuario.getCpfCnpj())
                .build();

        return entidade;
    }
}
