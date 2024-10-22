package com.crud.java.service;

import com.crud.java.application.model.dto.UsuarioDTO;
import com.crud.java.application.model.entity.UsuarioEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    ResponseEntity<Object> cadastrarUsuario(UsuarioDTO usuario);
    Optional<UsuarioEntity> consultarPeloCpfCnpj(String id);

    void deletarUsuario(String id);

    void editarUsuario(String id, UsuarioDTO usuario);

    List<Double> listaTemperatura(List<Double> temperaturas);
}
