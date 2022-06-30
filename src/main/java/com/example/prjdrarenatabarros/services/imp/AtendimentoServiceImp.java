package com.example.prjdrarenatabarros.services.imp;

import com.example.prjdrarenatabarros.domain.entity.Atendimento;
import com.example.prjdrarenatabarros.domain.repository.AtendimentoRepository;
import com.example.prjdrarenatabarros.services.AtendimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AtendimentoServiceImp implements AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;

    @Override
    public List<Atendimento> findAll() {
        return atendimentoRepository.findAll();
    }

    @Override
    public Atendimento findById(UUID id) {
        return atendimentoRepository.findById(id).isPresent() ? atendimentoRepository.findById(id).get() : null;
    }

    @Override
    public Atendimento save(Atendimento s) {
        return atendimentoRepository.save(s);
    }

    @Override
    public void delete(UUID id) {
        atendimentoRepository.deleteById(id);
    }
}
