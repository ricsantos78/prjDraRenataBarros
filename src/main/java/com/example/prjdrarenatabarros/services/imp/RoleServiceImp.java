package com.example.prjdrarenatabarros.services.imp;

import com.example.prjdrarenatabarros.domain.entity.Especialidade;
import com.example.prjdrarenatabarros.domain.entity.Role;
import com.example.prjdrarenatabarros.domain.repository.EspecialidadeRepository;
import com.example.prjdrarenatabarros.domain.repository.RoleRepository;
import com.example.prjdrarenatabarros.services.EspecialidadeService;
import com.example.prjdrarenatabarros.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp implements RoleService {

    @Autowired
    private RoleRepository repository;

    @Override
    public Iterable<Role> findAll() {
        return repository.findAll();
    }

    @Override
    public Role find(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public Role save(Role s) {
        return repository.save(s);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
