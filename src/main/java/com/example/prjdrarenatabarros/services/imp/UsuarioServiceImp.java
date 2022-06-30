package com.example.prjdrarenatabarros.services.imp;

import com.example.prjdrarenatabarros.domain.entity.Usuario;
import com.example.prjdrarenatabarros.domain.repository.UsuarioRepository;
import com.example.prjdrarenatabarros.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImp implements UsuarioService, UserDetailsService {


    private final UsuarioRepository usuarioRepository;

    @Override
    public Iterable<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario find(UUID id) {
        return  usuarioRepository.findById(id).isPresent() ? usuarioRepository.findById(id).get() : null;
    }

    @Override
    public Usuario save(Usuario s) {
        return usuarioRepository.save(s);
    }

    @Override
    public void delete(UUID id) {
         usuarioRepository.deleteById(id);
    }

    @Override
    public Optional<Usuario> findUsuarioByNome(String nome) {
        return usuarioRepository.findUsuarioByNome(nome);
    }

    @Override
    public Optional<Usuario> findUsuarioByEspecialidadeId(UUID id) {
        return usuarioRepository.findUsuarioByEspecialidadeId(id);
    }

    @Override
    public Usuario findUsuarioByLogin(String login) {
        return usuarioRepository.findUsuarioByLogin(login);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findUsuarioByLogin(s);

        if(usuario == null){
            throw new UsernameNotFoundException("Usuário não foi encontrado");
        }
        return new User(usuario.getLogin(),
                        usuario.getSenha(),
                        usuario.isEnabled(),
                        usuario.isAccountNonExpired(),
                        usuario.isCredentialsNonExpired(),
                        usuario.isAccountNonLocked(),
                        usuario.getAuthorities());
    }
}
