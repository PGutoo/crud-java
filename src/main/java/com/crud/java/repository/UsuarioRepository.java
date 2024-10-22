package com.crud.java.repository;

import com.crud.java.application.model.dto.UsuarioDTO;
import com.crud.java.application.model.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, String> {

    UsuarioEntity save(UsuarioDTO usuario);

    Optional<UsuarioEntity> findById(String id);

    void deleteById(String id);
}
