package com.example.prjdrarenatabarros.services.imp;

import com.example.prjdrarenatabarros.domain.entity.Especialidade;
import com.example.prjdrarenatabarros.domain.repository.EspecialidadeRepository;
import com.example.prjdrarenatabarros.services.EspecialidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EspecialidadeServiceImp implements EspecialidadeService {

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @Override
    public Iterable<Especialidade> findAll() {
        return especialidadeRepository.findAll();
    }

    @Override
    public Especialidade find(Long id) {
        return especialidadeRepository.findById(id).get();
    }

    @Override
    public Especialidade save(Especialidade s) {
        return especialidadeRepository.save(s);
    }

    @Override
    public void delete(Long id) {
        especialidadeRepository.deleteById(id);
    }

    @Override
    public Iterable<Especialidade> findEspecialidadeByName(String nome) {
        return especialidadeRepository.findEspecialidadeByName(nome);
    }
}
