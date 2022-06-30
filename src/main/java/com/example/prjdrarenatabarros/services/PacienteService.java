package com.example.prjdrarenatabarros.services;

import com.example.prjdrarenatabarros.domain.entity.Paciente;

import java.util.UUID;

public interface PacienteService {

    Iterable<Paciente> findAll();
    Paciente find(UUID id);
    Paciente save(Paciente s);
    void delete(UUID id);
    Iterable<Paciente> findPacienteByNome(String nome);
    Paciente findPacienteByCpf(String cpf);

}
