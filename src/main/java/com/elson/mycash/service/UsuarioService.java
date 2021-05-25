package com.elson.mycash.service;

import com.elson.mycash.domain.Usuario;
import com.elson.mycash.domain.UsuarioRole;
import com.elson.mycash.exception.UsuarioException;
import com.elson.mycash.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> todos() {
        return repo.findAll();
    }

    public void registraUsuarioAdmin(String email, String senha) {
        if (repo.findByEmail(email).isEmpty()) {
            Usuario usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setSenha(passwordEncoder.encode(senha));
            usuario.setRole(UsuarioRole.ROLE_ADMIN);
            repo.save(usuario);
        }
    }

    public Usuario save(String email, String senha) {
        Usuario usuario= new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(passwordEncoder.encode(senha));
        usuario.setRole(UsuarioRole.ROLE_USER);

        if (repo.findByEmail(email).isPresent()){
            throw new UsuarioException("Ja existe usuario com esse e-mail");
        }

        return repo.save(usuario);
    }

    public Usuario findByEmail(String email) {
        return repo.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException());
    }

    public Usuario resetarSenha(String email, String senhaNova) {
        Usuario usuario = findByEmail(email);
        usuario.setSenha(passwordEncoder.encode(senhaNova));
        return repo.save(usuario);
    }
}
