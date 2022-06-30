package com.example.prjdrarenatabarros.domain.repository;

import com.example.prjdrarenatabarros.domain.entity.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, UUID> {
}
