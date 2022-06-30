package com.example.prjdrarenatabarros.domain.repository;

import com.example.prjdrarenatabarros.domain.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface PacienteRepository extends JpaRepository<Paciente, UUID> {


    List<Paciente> findPacienteByNome(String nome);

    Paciente findPacienteByCpf(String cpf);
}
