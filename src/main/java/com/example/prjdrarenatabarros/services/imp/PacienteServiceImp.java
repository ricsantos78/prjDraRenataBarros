package com.example.prjdrarenatabarros.services.imp;

import com.example.prjdrarenatabarros.domain.entity.Paciente;
import com.example.prjdrarenatabarros.domain.repository.PacienteRepository;
import com.example.prjdrarenatabarros.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PacienteServiceImp implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public Iterable<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente find(Long id) {
        return pacienteRepository.findById(id).get();
    }

    @Override
    public Paciente save(Paciente s) {
        return pacienteRepository.save(s);
    }

    @Override
    public void delete(Long id) {
        pacienteRepository.deleteById(id);
    }

    @Override
    public Iterable<Paciente> findPacienteByName(String nome) {
        return pacienteRepository.findPacienteByName(nome);
    }


}
