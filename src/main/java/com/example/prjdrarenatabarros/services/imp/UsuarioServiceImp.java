package com.example.prjdrarenatabarros.services.imp;

import com.example.prjdrarenatabarros.domain.entity.Usuario;
import com.example.prjdrarenatabarros.domain.repository.UsuarioRepository;
import com.example.prjdrarenatabarros.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UsuarioServiceImp implements UsuarioService, UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Iterable<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario find(Long id) {
        return  usuarioRepository.findById(id).get();
    }

    @Override
    public Usuario save(Usuario s) {
        return usuarioRepository.save(s);
    }

    @Override
    public void delete(Long id) {
         usuarioRepository.deleteById(id);
    }

    @Override
    public Iterable<Usuario> findUsuarioByName(String nome) {
        return usuarioRepository.findUsuarioByName(nome);
    }

    @Override
    public List<Usuario> findUsuarioByEspecialidade(Long id) {
        return usuarioRepository.findUsuarioByEspecialidade(id);
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
