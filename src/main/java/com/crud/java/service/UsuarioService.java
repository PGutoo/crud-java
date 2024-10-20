package com.crud.java.service;

import com.crud.java.application.model.dto.UsuarioDTO;
import com.crud.java.application.model.entity.UsuarioEntity;
import org.springframework.http.HttpEntity;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    HttpEntity<Object> cadastrarUsuario(UsuarioDTO usuario);
    Optional<UsuarioEntity> consultarPeloId(String id);

    void deletarUsuario(String id);

    void editarUsuario(String id, UsuarioDTO usuario);

    List<Double> listaTemperatura(List<Double> temperaturas);
}
