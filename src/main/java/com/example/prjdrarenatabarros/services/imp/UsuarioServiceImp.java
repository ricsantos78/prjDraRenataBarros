package com.example.prjdrarenatabarros.services.imp;

import com.example.prjdrarenatabarros.domain.entity.Usuario;
import com.example.prjdrarenatabarros.domain.repository.UsuarioRepository;
import com.example.prjdrarenatabarros.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImp implements UsuarioService {

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
}
