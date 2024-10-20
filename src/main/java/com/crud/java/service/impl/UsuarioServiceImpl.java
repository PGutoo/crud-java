package com.crud.java.service.impl;

import com.crud.java.application.mapper.UsuarioMapper;
import com.crud.java.application.model.dto.UsuarioDTO;
import com.crud.java.application.model.entity.UsuarioEntity;
import com.crud.java.application.mapper.UsuarioMapperConverter;
import com.crud.java.repository.UsuarioRepository;
import com.crud.java.service.UsuarioService;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    private final UsuarioRepository cadastroUsuarioRepository;
    private final UsuarioMapperConverter usuarioConverter;

    public UsuarioServiceImpl(final UsuarioRepository cadastroUsuarioRepository,
                              final UsuarioMapperConverter usuarioConverter) {
        this.cadastroUsuarioRepository = cadastroUsuarioRepository;
        this.usuarioConverter = usuarioConverter;
    }

    @Override
    public HttpEntity<Object> cadastrarUsuario(UsuarioDTO usuario){

        LOGGER.info("Cadastrando um novo usuário {}", usuario.getId());
        try {
            UsuarioEntity usuarioEntity = usuarioConverter.conversorEntidade(usuario);
            LOGGER.info("Cadastrando um novo usuário {}", usuarioEntity.getId());

            if (cadastroUsuarioRepository.findById(usuarioEntity.getId()).isEmpty()){
                LOGGER.info("Entidade {}", usuarioEntity);
//                UsuarioEntity usuarioEntitySecond = UsuarioMapper.INSTANCE.objToEntity(usuario);

//                LOGGER.info("Cadastrando um novo usuário {}", usuarioEntitySecond.getId());
                cadastroUsuarioRepository.save(usuarioEntity);
                return ResponseEntity.status(HttpStatus.CREATED).body(usuarioEntity);
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(usuarioEntity);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
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
    public void editarUsuario(String id, UsuarioDTO usuario) {

    }

//    @Override
//    public List<Double> listaTemperatura(List<Double> temperaturas) {
//        List<Double> highTemperature = new ArrayList<>();
//        for (Double temp : temperaturas) {
//            if (temp > 30) {
//                highTemperature.add(temp);
//            }
//        }
//        return highTemperature;
//    }

    public List<Double> listaTemperatura(List<Double> temperaturas) {
        return temperaturas.stream()
                .filter(temperatura -> temperatura > 30)
                .collect(Collectors.toList());
    }

}
