package com.crud.java.rest;

import com.crud.java.application.model.Usuario;
import com.crud.java.application.model.entity.UsuarioEntity;
import com.crud.java.repository.UsuarioRepository;
import com.crud.java.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/crud")
public class CrudController {

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

    //TODO Cadastrar um Usuario
    @PostMapping("/cadastrar")
    public HttpEntity<Object> cadastrarUsuario(@RequestBody Usuario usuario){
        return cadastroUsuarioService.cadastrarUsuario(usuario);
    }

    //TODO Consultar um usuario por id.
    @GetMapping("/consultar/{id}")
    public ResponseEntity<Object> consultarUsuario(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.OK).body(cadastroUsuarioService.consultarPeloId(id));
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
