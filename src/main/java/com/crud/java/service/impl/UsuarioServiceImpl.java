package com.crud.java.service.impl;

import com.crud.java.application.model.dto.UsuarioDTO;
import com.crud.java.application.model.entity.UsuarioEntity;
import com.crud.java.domain.Data;
import com.crud.java.repository.UsuarioRepository;
import com.crud.java.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    public UsuarioServiceImpl(final UsuarioRepository cadastroUsuarioRepository) {
        this.cadastroUsuarioRepository = cadastroUsuarioRepository;
    }

    @Override
    public ResponseEntity<Object> cadastrarUsuario(UsuarioDTO usuarioDTO){

        LOGGER.info("Cadastrando um novo usuário {}", usuarioDTO.getCpfCnpj());
        try {
            if (cadastroUsuarioRepository.findById(usuarioDTO.getCpfCnpj()).isEmpty()){
                UsuarioEntity entidadeUsuario = UsuarioEntity.builder()
                        .email(usuarioDTO.getEmail())
                        .nome(usuarioDTO.getNome())
                        .cpfCnpj(usuarioDTO.getCpfCnpj())
                        .idade(usuarioDTO.getIdade())
                        .build();
                LOGGER.info("Entidade {}", entidadeUsuario);
                cadastroUsuarioRepository.save(entidadeUsuario);
                return ResponseEntity.status(HttpStatus.CREATED).body(new Data<>(entidadeUsuario));
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new Data<>(usuarioDTO));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Optional<UsuarioEntity> consultarPeloCpfCnpj(String cpfCnpj) {
        //TODO Implementar tratamento
        return cadastroUsuarioRepository.findById(cpfCnpj);
    }

    @Override
    public void deletarUsuario(String id) {
        cadastroUsuarioRepository.deleteById(id);
    }

    @Override
    public void editarUsuario(String id, UsuarioDTO usuario) {

    }

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
