package com.crud.java.service;

import com.crud.java.application.model.Usuario;
import com.crud.java.application.model.entity.UsuarioEntity;
import org.springframework.http.HttpEntity;

import java.util.Optional;

public interface UsuarioService {

    HttpEntity<Object> cadastrarUsuario(Usuario usuario);
    Optional<UsuarioEntity> consultarPeloId(String id);

    void deletarUsuario(String id);

    void editarUsuario(String id, Usuario usuario);
}
