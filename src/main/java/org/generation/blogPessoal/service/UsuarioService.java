package org.generation.blogPessoal.service;

import java.nio.charset.Charset;
import org.apache.commons.codec.binary.Base64;

import java.util.List;
import java.util.Optional;

import org.generation.blogPessoal.model.UserLogin;
import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	@Autowired
    private UsuarioRepository repository;

    public List<Usuario> listarUsuarios(){

        return repository.findAll();

    }

    public Optional<Usuario> cadastrarUsuario(Usuario usuario) {

        if (repository.findByUsuario(usuario.getUsuario()).isPresent())
            return Optional.empty();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String senhaEncoder = encoder.encode(usuario.getSenha());

        usuario.setSenha(senhaEncoder);

        return Optional.of(repository.save(usuario));

    }

    public Optional<UserLogin> autenticarUsuario(Optional<UserLogin> user) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Optional<Usuario> usuario = repository.findByUsuario(user.get().getUsuario());

        if (usuario.isPresent()) {
            if (encoder.matches(user.get().getSenha(), usuario.get().getSenha())) {

                String auth = user.get().getUsuario() + ":" + user.get().getSenha();
                byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
                String authHeader = "Basic " + new String(encodedAuth);

                user.get().setId(usuario.get().getId());
                user.get().setToken(authHeader);
                user.get().setNome(usuario.get().getNome());
                user.get().setSenha(usuario.get().getSenha());
                user.get().setFoto(usuario.get().getFoto());
                user.get().setTipo(usuario.get().getTipo());
                

                return user;

            }
        }
        return null;
    }
}