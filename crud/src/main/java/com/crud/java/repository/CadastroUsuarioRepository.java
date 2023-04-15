package com.crud.java.repository;

import com.crud.java.application.model.Usuario;
import com.crud.java.application.model.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CadastroUsuarioRepository extends JpaRepository<UsuarioEntity, String> {

    UsuarioEntity save(Usuario usuario);
    Optional<UsuarioEntity> findById(String id);

    void deleteById(String id);
}
