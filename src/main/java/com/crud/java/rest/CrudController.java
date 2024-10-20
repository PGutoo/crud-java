package com.crud.java.rest;

import com.crud.java.application.model.dto.UsuarioDTO;
import com.crud.java.application.model.entity.UsuarioEntity;
import com.crud.java.domain.CpfCnpjUtils;
import com.crud.java.domain.Data;
import com.crud.java.repository.UsuarioRepository;
import com.crud.java.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/crud")
public class CrudController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrudController.class);

    @Autowired
    private UsuarioService cadastroUsuarioService;

    @Autowired
    private UsuarioRepository cadastroUsuarioRepository;

    //Chama API Externa
    @GetMapping("/externa")
    public String chamarApiExterna(){
        String uri = "https://api.adviceslip.com/advice";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return result;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastrarUsuario(@RequestBody UsuarioDTO usuario){
        LOGGER.info("Cadastrando um novo usuário {}", usuario.getCpfCnpj());
        if(CpfCnpjUtils.isCpfOrCnpjValid(usuario.getCpfCnpj())){
            return cadastroUsuarioService.cadastrarUsuario(usuario);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Data<>("CPF/CNPJ inválido"));
        }
    }

    @GetMapping("/consultar/{cpfCnpj}")
    public ResponseEntity<Object> consultarUsuario(@PathVariable String cpfCnpj){
        if(CpfCnpjUtils.isCpfOrCnpjValid(cpfCnpj)) {
            return ResponseEntity.status(HttpStatus.OK).body(cadastroUsuarioService.consultarPeloCpfCnpj(cpfCnpj));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Data<>("CPF/CNPJ inválido"));
        }
    }

    //TODO Deletar um usuario
    @DeleteMapping("/deletar/{id}")
    public HttpStatus deletarUsuario(@PathVariable String id){
        cadastroUsuarioService.deletarUsuario(id);
        return HttpStatus.OK;
    }

    //TODO Editar um usuario inserindo somente
    @PatchMapping("/editar")
    public HttpStatus editarUsuario(@RequestBody UsuarioEntity usuario){
        cadastroUsuarioRepository.save(usuario);
        return HttpStatus.OK;
    }

    @GetMapping("/status")
    public HttpStatus consultarStatus(){
        return HttpStatus.OK;
    }


}
