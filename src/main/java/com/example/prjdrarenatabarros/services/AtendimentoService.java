package com.example.prjdrarenatabarros.services;

import com.example.prjdrarenatabarros.domain.entity.Atendimento;

import java.util.List;
import java.util.UUID;

public interface AtendimentoService {

    List<Atendimento> findAll();
    Atendimento findById(UUID id);
    Atendimento save(Atendimento s);
    void delete(UUID id);

}
