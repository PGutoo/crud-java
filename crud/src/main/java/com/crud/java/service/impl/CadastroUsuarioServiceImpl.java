package com.crud.java.service.impl;

import com.crud.java.application.model.Usuario;
import com.crud.java.application.model.entity.UsuarioEntity;
import com.crud.java.application.model.mapper.UsuarioMapperConverter;
import com.crud.java.repository.CadastroUsuarioRepository;
import com.crud.java.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroUsuarioServiceImpl implements CadastroUsuarioService {

    @Autowired
    private CadastroUsuarioRepository cadastroUsuarioRepository;

    @Autowired
    private UsuarioMapperConverter usuarioConverter;

    @Override
    public HttpEntity<Object> cadastrarUsuario(Usuario usuario){
        if (!cadastroUsuarioRepository.findById(usuario.getId()).isPresent()){
            UsuarioEntity usuarioEntity = usuarioConverter.conversorEntidade(usuario);
            cadastroUsuarioRepository.save(usuarioEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(usuario);
        }
    }

    @Override
    public Optional<UsuarioEntity> consultarPeloId(String id) {
        return cadastroUsuarioRepository.findById(id);
    }

    @Override
    public void deletarUsuario(String id) {
        cadastroUsuarioRepository.deleteById(id);
    }

    @Override
    public void editarUsuario(String id, Usuario usuario) {

    }

}
