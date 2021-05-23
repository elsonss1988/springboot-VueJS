package com.elson.mycash.web.api;

import com.elson.mycash.domain.Usuario;
import com.elson.mycash.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<Usuario> todos(){return service.todos();}

    @PostMapping
    public Usuario criar(
            @RequestParam (required = true) String email,
            @RequestParam (required = true) String senha){
        return service.save(email,senha);
    }

    @GetMapping("/{email}")
    public Usuario apenasUm(@PathVariable String email){
        return service.findByEmail(email);
    }

    @PutMapping("/{email}")
    public Usuario resetarSenha(
            @PathVariable("email") String email,
            @RequestParam(required=true) String senhaNova){
        return service.resetarSenha(email, senhaNova);
    }
}
