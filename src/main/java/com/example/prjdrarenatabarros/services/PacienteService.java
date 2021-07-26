package com.example.prjdrarenatabarros.services;

import com.example.prjdrarenatabarros.domain.entity.Paciente;

public interface PacienteService {

    public Iterable<Paciente> findAll();
    public Paciente find(Long id);
    public Paciente save(Paciente s);
    public void delete(Long id);
    public Iterable<Paciente> findPacienteByName(String nome);
    public Paciente findPacienteByCpf(String cpf);

}
