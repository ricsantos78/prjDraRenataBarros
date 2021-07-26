package com.example.prjdrarenatabarros.services;

import com.example.prjdrarenatabarros.domain.entity.Especialidade;
import com.example.prjdrarenatabarros.domain.entity.Role;
import com.example.prjdrarenatabarros.domain.entity.UsuarioRole;

public interface UsuarioRoleService {

    public Iterable<UsuarioRole> findAll();
    public UsuarioRole find(Long id);
    public UsuarioRole save(UsuarioRole s);
    public void delete(Long id);
    public UsuarioRole findUsuarioRole(Long idu, Long idr);

}
