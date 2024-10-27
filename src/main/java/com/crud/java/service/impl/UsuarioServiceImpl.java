package com.crud.java.service.impl;

import com.crud.java.application.model.dto.UsuarioDTO;
import com.crud.java.application.model.entity.UsuarioEntity;
import com.crud.java.domain.Data;
import com.crud.java.repository.UsuarioRepository;
import com.crud.java.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(final UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public ResponseEntity<Object> cadastrarUsuario(UsuarioDTO usuarioDTO){

        try {
            LOGGER.info("Cadastrando um novo usuário {}", usuarioDTO.getCpfCnpj());
            if (usuarioRepository.findById(usuarioDTO.getCpfCnpj()).isEmpty()){
                UsuarioEntity entidadeUsuario = UsuarioEntity.builder()
                        .email(usuarioDTO.getEmail())
                        .nome(usuarioDTO.getNome())
                        .cpfCnpj(usuarioDTO.getCpfCnpj())
                        .idade(usuarioDTO.getIdade())
                        .build();
                usuarioRepository.save(entidadeUsuario);
                return ResponseEntity.status(HttpStatus.CREATED).body(new Data<>(201, entidadeUsuario, "Usuário criado"));
            } else {
                LOGGER.error("Usuário já cadastrado {}", usuarioDTO);
                return ResponseEntity.status(HttpStatus.CONFLICT).body(new Data<>(409, usuarioDTO, "Usuário já existe"));
            }
        } catch (Exception e) {
            LOGGER.error("Erro ao cadastrar usuário {}", usuarioDTO, e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public ResponseEntity<Object> consultarPeloCpfCnpj(String cpfCnpj) {
        try {
            LOGGER.info("Consultando usuário {}", cpfCnpj);
            var dadosUsuario = usuarioRepository.findById(cpfCnpj);

            if (dadosUsuario.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(new Data<>(200, dadosUsuario, "Usuário encontrado"));
            }else {
                LOGGER.error("Usuário não encontrado {}", cpfCnpj);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        }catch (Exception e){
            LOGGER.error("Erro ao consultar usuário {}", cpfCnpj, e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Data<>(400, cpfCnpj, "CPF/CNPJ inválido"));
        }
    }

    @Override
    public ResponseEntity<Object> listarUsuarios() {
        try {
            LOGGER.info("Consultando todos os usuários");
            List<UsuarioEntity> usuarios = usuarioRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(new Data<>(200, usuarios, "Usuários encontrados"));
        }catch (Exception e){
            LOGGER.error("Erro ao consultar usuários {}", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Data<>(400, null, "Erro ao consultar usuários"));
        }
    }

    @Override
    public void deletarUsuario(String cpfCnpj) {
        try {
            LOGGER.info("Deletando usuário {}", cpfCnpj);
            if (usuarioRepository.findById(cpfCnpj).isPresent()) {
                usuarioRepository.deleteById(cpfCnpj);
            }else {
                LOGGER.error("Usuário não encontrado {}", cpfCnpj);
                throw new Exception("Usuário não encontrado");
            }
        }catch (Exception e){
            LOGGER.error("Erro ao deletar usuário {}", cpfCnpj, e);
            throw new RuntimeException(e);
        }
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
