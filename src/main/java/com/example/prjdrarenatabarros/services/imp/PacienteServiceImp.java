package com.example.prjdrarenatabarros.services.imp;

import com.example.prjdrarenatabarros.domain.entity.Paciente;
import com.example.prjdrarenatabarros.domain.repository.PacienteRepository;
import com.example.prjdrarenatabarros.services.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PacienteServiceImp implements PacienteService {
    private final PacienteRepository pacienteRepository;

    @Override
    public Iterable<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente find(UUID id) {
        return pacienteRepository.findById(id).isPresent() ? pacienteRepository.findById(id).get() : null;
    }

    @Override
    public Paciente save(Paciente s) {
        return pacienteRepository.save(s);
    }

    @Override
    public void delete(UUID id) {
        pacienteRepository.deleteById(id);
    }

    @Override
    public Iterable<Paciente> findPacienteByNome(String nome) {
        return pacienteRepository.findPacienteByNome(nome);
    }

    @Override
    public Paciente findPacienteByCpf(String cpf) {
        return pacienteRepository.findPacienteByCpf(cpf);
    }


}
