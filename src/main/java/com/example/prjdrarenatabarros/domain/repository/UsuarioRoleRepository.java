package com.example.prjdrarenatabarros.domain.repository;

import com.example.prjdrarenatabarros.domain.entity.Especialidade;
import com.example.prjdrarenatabarros.domain.entity.Role;
import com.example.prjdrarenatabarros.domain.entity.Usuario;
import com.example.prjdrarenatabarros.domain.entity.UsuarioRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UsuarioRoleRepository extends CrudRepository<UsuarioRole, Long> {


    @Query("select u from UsuarioRole u " +
            "inner join Usuario ur on u.usuario.id = ur.id " +
            "inner join Role r on u.role.id = r.id " +
            "where ur.id = ?1 and r.id = ?2")
    UsuarioRole findByUsuarioByRole(Long idUsuario, Long roleid);




}
