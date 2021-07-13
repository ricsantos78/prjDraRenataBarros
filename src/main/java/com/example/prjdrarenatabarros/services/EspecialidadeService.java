package com.example.prjdrarenatabarros.services;

import com.example.prjdrarenatabarros.domain.entity.Especialidade;

public interface EspecialidadeService {

    public Iterable<Especialidade> findAll();
    public Especialidade find(Long id);
    public Especialidade save(Especialidade s);
    public void delete(Long id);
    public Iterable<Especialidade> findEspecialidadeByName(String nome);

}
