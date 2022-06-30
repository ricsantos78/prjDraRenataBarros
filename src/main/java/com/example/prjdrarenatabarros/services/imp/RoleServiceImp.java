package com.example.prjdrarenatabarros.services.imp;

import com.example.prjdrarenatabarros.domain.entity.Role;
import com.example.prjdrarenatabarros.domain.repository.RoleRepository;
import com.example.prjdrarenatabarros.services.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RoleServiceImp implements RoleService {

    private final RoleRepository repository;

    @Override
    public Iterable<Role> findAll() {
        return repository.findAll();
    }

    @Override
    public Role find(UUID id) {
        return repository.findById(id).isPresent() ? repository.findById(id).get() : null;
    }

    @Override
    public Role save(Role s) {
        return repository.save(s);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
