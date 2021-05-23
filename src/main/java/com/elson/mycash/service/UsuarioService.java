package com.elson.mycash.service;

import com.elson.mycash.domain.Usuario;
import com.elson.mycash.domain.UsuarioRole;
import com.elson.mycash.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    public List<Usuario> todos() {
        return repo.findAll();
    }

    public void registraUsuarioAdmin(String email, String senha) {
        if (repo.findByEmail(email).isEmpty()) {
            Usuario usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setSenha(senha);
            usuario.setRole(UsuarioRole.ROLE_ADMIN);
            repo.save(usuario);
        }
    }
}
