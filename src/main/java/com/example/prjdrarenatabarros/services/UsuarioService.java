package com.example.prjdrarenatabarros.services;

import com.example.prjdrarenatabarros.domain.entity.Usuario;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioService {

    Iterable<Usuario> findAll();
    Usuario find(UUID id);
    Usuario save(Usuario s);
    void delete(UUID id);
    Optional<Usuario> findUsuarioByNome(String nome);
    Optional<Usuario> findUsuarioByEspecialidadeId(UUID id);
    Usuario findUsuarioByLogin(String login);

}
