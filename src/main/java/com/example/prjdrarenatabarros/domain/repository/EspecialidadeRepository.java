package com.example.prjdrarenatabarros.domain.repository;

import com.example.prjdrarenatabarros.domain.entity.Especialidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EspecialidadeRepository extends JpaRepository<Especialidade, UUID> {


    List<Especialidade> findEspecialidadeByNome(String nome);

}
