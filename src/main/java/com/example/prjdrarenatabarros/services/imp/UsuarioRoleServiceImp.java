package com.example.prjdrarenatabarros.services.imp;

import com.example.prjdrarenatabarros.domain.entity.Especialidade;
import com.example.prjdrarenatabarros.domain.entity.Role;
import com.example.prjdrarenatabarros.domain.entity.UsuarioRole;
import com.example.prjdrarenatabarros.domain.repository.EspecialidadeRepository;
import com.example.prjdrarenatabarros.domain.repository.RoleRepository;
import com.example.prjdrarenatabarros.domain.repository.UsuarioRoleRepository;
import com.example.prjdrarenatabarros.services.EspecialidadeService;
import com.example.prjdrarenatabarros.services.RoleService;
import com.example.prjdrarenatabarros.services.UsuarioRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioRoleServiceImp implements UsuarioRoleService {

    @Autowired
    private UsuarioRoleRepository repository;


    @Override
    public Iterable<UsuarioRole> findAll() {
        return repository.findAll();
    }

    @Override
    public UsuarioRole find(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public UsuarioRole save(UsuarioRole s) {
        return repository.save(s);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public UsuarioRole findUsuarioRole(Long idu, Long idr) {
        return repository.findByUsuarioByRole(idu, idr);
    }
}
