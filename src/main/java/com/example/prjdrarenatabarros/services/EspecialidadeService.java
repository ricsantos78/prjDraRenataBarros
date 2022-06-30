package com.example.prjdrarenatabarros.services;

import com.example.prjdrarenatabarros.domain.entity.Especialidade;

import java.util.UUID;

public interface EspecialidadeService {

    Iterable<Especialidade> findAll();
    Especialidade find(UUID id);
    Especialidade save(Especialidade s);
    void delete(UUID id);
    Iterable<Especialidade> findEspecialidadeByNome(String nome);

}
