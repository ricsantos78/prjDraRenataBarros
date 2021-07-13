package com.example.prjdrarenatabarros.services;

import com.example.prjdrarenatabarros.domain.entity.Atendimento;

public interface AtendimentoService {

    public Iterable<Atendimento> findAll();
    public Atendimento find(Long id);
    public Atendimento save(Atendimento s);
    public void delete(Long id);
//    public Iterable<Usuario> findUsuarioByName(String nome);
//    public List<Usuario> findUsuarioByEspecialidade(Long id);

}
