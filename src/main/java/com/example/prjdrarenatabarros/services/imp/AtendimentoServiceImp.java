package com.example.prjdrarenatabarros.services.imp;

import com.example.prjdrarenatabarros.domain.entity.Atendimento;
import com.example.prjdrarenatabarros.domain.entity.Especialidade;
import com.example.prjdrarenatabarros.domain.repository.AtendimentoRepository;
import com.example.prjdrarenatabarros.domain.repository.EspecialidadeRepository;
import com.example.prjdrarenatabarros.services.AtendimentoService;
import com.example.prjdrarenatabarros.services.EspecialidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtendimentoServiceImp implements AtendimentoService {

    @Autowired
    private AtendimentoRepository atendimentoRepository;


    @Override
    public Iterable<Atendimento> findAll() {
        return atendimentoRepository.findAll();
    }

    @Override
    public Atendimento find(Long id) {
        return atendimentoRepository.findById(id).get();
    }

    @Override
    public Atendimento save(Atendimento s) {
        return atendimentoRepository.save(s);
    }

    @Override
    public void delete(Long id) {
        atendimentoRepository.deleteById(id);
    }
}
