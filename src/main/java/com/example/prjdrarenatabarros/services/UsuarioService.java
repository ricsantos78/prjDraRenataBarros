package com.example.prjdrarenatabarros.services;

import com.example.prjdrarenatabarros.domain.entity.Usuario;

import java.util.List;

public interface UsuarioService {

    public Iterable<Usuario> findAll();
    public Usuario find(Long id);
    public Usuario save(Usuario s);
    public void delete(Long id);
    public Iterable<Usuario> findUsuarioByName(String nome);
    public List<Usuario> findUsuarioByEspecialidade(Long id);
    public Usuario findUsuarioByLogin(String login);

}
