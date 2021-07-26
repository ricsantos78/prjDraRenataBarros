package com.example.prjdrarenatabarros.services;

import com.example.prjdrarenatabarros.domain.entity.Especialidade;
import com.example.prjdrarenatabarros.domain.entity.Role;

public interface RoleService {

    public Iterable<Role> findAll();
    public Role find(Long id);
    public Role save(Role s);
    public void delete(Long id);
//    public Iterable<Role> findEspecialidadeByName(String nome);

}
