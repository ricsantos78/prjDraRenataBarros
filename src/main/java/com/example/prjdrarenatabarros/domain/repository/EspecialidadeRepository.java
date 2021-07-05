package com.example.prjdrarenatabarros.domain.repository;

import com.example.prjdrarenatabarros.domain.entity.Especialidade;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface EspecialidadeRepository extends CrudRepository<Especialidade, Long> {

    @Query("select u from Especialidade u where u.nome like %?1%")
    List<Especialidade> findEspecialidadeByName(String nome);
}
