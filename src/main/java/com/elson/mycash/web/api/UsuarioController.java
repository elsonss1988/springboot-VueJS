package com.elson.mycash.web.api;

import com.elson.mycash.domain.Usuario;
import com.elson.mycash.service.UsuarioService;
import com.elson.mycash.web.api.dto.UsuarioDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    //public List<Usuario> todos(){return service.todos();}

    public List<UsuarioDTO> todos() {
        ModelMapper mapper = new ModelMapper();
        List<Usuario> todos = service.todos();
        List<UsuarioDTO> usuariosDTO = todos
                .stream()
                .map((usuario) -> mapper.map(usuario, UsuarioDTO.class))
                .collect(Collectors.toList());
        return usuariosDTO;
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Usuario criar(
            @RequestParam (required = true) String email,
            @RequestParam (required = true) String senha){
        return service.save(email,senha);
    }

    @GetMapping("/{email}")
    @PreAuthorize("#email == authentication.getName() or hasRole('ROLE_ADMIN')")
    public Usuario apenasUm(
            Authentication authentication,
            @PathVariable String email){
        return service.findByEmail(email);
    }

    @PutMapping("/{email}")
    public Usuario resetarSenha(
            @PathVariable("email") String email,
            @RequestParam(required=true) String senhaNova){
        return service.resetarSenha(email, senhaNova);
    }
}
