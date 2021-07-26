package com.example.prjdrarenatabarros.domain.repository;

import com.example.prjdrarenatabarros.domain.entity.Paciente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface PacienteRepository extends CrudRepository<Paciente, Long> {

    @Query("select u from Paciente u where u.nome like %?1%")
    List<Paciente> findPacienteByName(String nome);

    @Query("select u from Paciente u where u.cpf = ?1")
    Paciente findPacienteByCpf(String cpf);
}
