package com.example.prjdrarenatabarros.services.imp;

import com.example.prjdrarenatabarros.domain.entity.UsuarioRole;
import com.example.prjdrarenatabarros.domain.repository.UsuarioRoleRepository;
import com.example.prjdrarenatabarros.services.UsuarioRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UsuarioRoleServiceImp implements UsuarioRoleService {

    private final UsuarioRoleRepository repository;


    @Override
    public Iterable<UsuarioRole> findAll() {
        return repository.findAll();
    }

    @Override
    public UsuarioRole find(UUID id) {
        return repository.findById(id).isPresent() ? repository.findById(id).get() : null;
    }

    @Override
    public UsuarioRole save(UsuarioRole s) {
        return repository.save(s);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public UsuarioRole findUsuarioRole(UUID idu, UUID idr) {
        return repository.findByUsuarioByRole(idu, idr);
    }
}
