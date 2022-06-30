package com.example.prjdrarenatabarros.services;

import com.example.prjdrarenatabarros.domain.entity.Especialidade;
import com.example.prjdrarenatabarros.domain.entity.Role;
import com.example.prjdrarenatabarros.domain.entity.UsuarioRole;

import java.util.UUID;

public interface UsuarioRoleService {

    Iterable<UsuarioRole> findAll();
    UsuarioRole find(UUID id);
    UsuarioRole save(UsuarioRole s);
    void delete(UUID id);
    UsuarioRole findUsuarioRole(UUID idu, UUID idr);

}
