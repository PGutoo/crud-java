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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


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
        WebClient webClient = WebClient.create("https://roadmap-center-norte.vercel.app");
        return webClient.get()
                .uri("/status")
                .retrieve()// Envia a requisição e aguarda a resposta
                .bodyToMono(String.class)// Converte o corpo da resposta para uma String
                .block();// Para chamadas síncronas, usa block()
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Object> cadastrarUsuario(@RequestBody UsuarioDTO usuario){
        LOGGER.info("Cadastrando um novo usuário {}", usuario.getCpfCnpj());

        if(CpfCnpjUtils.isCpfOrCnpjValid(usuario.getCpfCnpj())){
            return cadastroUsuarioService.cadastrarUsuario(usuario);
        }else{
            LOGGER.error("CPF/CNPJ inválido");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Data<>(400, usuario.getCpfCnpj(),"CPF/CNPJ inválido"));
        }
    }

    @GetMapping("/consultar/{cpfCnpj}")
    public ResponseEntity<Object> consultarUsuario(@PathVariable String cpfCnpj){

        LOGGER.info("Consultando usuário {}", cpfCnpj);
        if(CpfCnpjUtils.isCpfOrCnpjValid(cpfCnpj)) {
            return cadastroUsuarioService.consultarPeloCpfCnpj(cpfCnpj);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Data<>(400,cpfCnpj, "CPF/CNPJ inválido"));
        }
    }

    @GetMapping("/consultar")
    public ResponseEntity<Object> listarUsuarios(){
        return cadastroUsuarioService.listarUsuarios();
    }

    //TODO Deletar um usuario
    @DeleteMapping("/deletar/{cpfCnpj}")
    public ResponseEntity<Object> deletarUsuario(@PathVariable String cpfCnpj){
        cadastroUsuarioService.deletarUsuario(cpfCnpj);
        return ResponseEntity.status(HttpStatus.OK).body(new Data<>(200, cpfCnpj,"Usuário deletado"));
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
