package com.example.prjdrarenatabarros.domain.repository;

import com.example.prjdrarenatabarros.domain.entity.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    @Query("select u from Usuario u where u.nome like %?1%")
    List<Usuario> findUsuarioByName(String nome);




}
