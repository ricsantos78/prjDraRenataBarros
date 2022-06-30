package com.example.prjdrarenatabarros.domain.repository;

import com.example.prjdrarenatabarros.domain.entity.UsuarioRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
public interface UsuarioRoleRepository extends JpaRepository<UsuarioRole, UUID> {


    @Query("select u from UsuarioRole u " +
            "inner join Usuario ur on u.usuario.id = ur.id " +
            "inner join Role r on u.role.id = r.id " +
            "where ur.id = ?1 and r.id = ?2")
    UsuarioRole findByUsuarioByRole(UUID idUsuario, UUID roleid);




}
