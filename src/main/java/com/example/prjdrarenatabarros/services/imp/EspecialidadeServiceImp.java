package com.example.prjdrarenatabarros.services.imp;

import com.example.prjdrarenatabarros.domain.entity.Especialidade;
import com.example.prjdrarenatabarros.domain.repository.EspecialidadeRepository;
import com.example.prjdrarenatabarros.services.EspecialidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EspecialidadeServiceImp implements EspecialidadeService {

    private final EspecialidadeRepository especialidadeRepository;

    @Override
    public Iterable<Especialidade> findAll() {
        return especialidadeRepository.findAll();
    }

    @Override
    public Especialidade find(UUID id) {
        return especialidadeRepository.findById(id).isPresent() ? especialidadeRepository.findById(id).get() : null;
    }

    @Override
    public Especialidade save(Especialidade s) {
        return especialidadeRepository.save(s);
    }

    @Override
    public void delete(UUID id) {
        especialidadeRepository.deleteById(id);
    }

    @Override
    public Iterable<Especialidade> findEspecialidadeByNome(String nome) {
        return especialidadeRepository.findEspecialidadeByNome(nome);
    }
}
