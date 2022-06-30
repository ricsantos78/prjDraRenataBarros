package com.example.prjdrarenatabarros.services;

import com.example.prjdrarenatabarros.domain.entity.Especialidade;
import com.example.prjdrarenatabarros.domain.entity.Role;

import java.util.UUID;

public interface RoleService {

    Iterable<Role> findAll();
    Role find(UUID id);
    Role save(Role s);
    void delete(UUID id);

}
