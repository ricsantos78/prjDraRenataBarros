package com.example.prjdrarenatabarros.domain.repository;


import com.example.prjdrarenatabarros.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findUsuarioByNome(String nome);
    Optional<Usuario> findUsuarioByEspecialidadeId(@Param("id") UUID id);
    Usuario findUsuarioByLogin(String login);



}
