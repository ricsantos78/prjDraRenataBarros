package com.example.prjdrarenatabarros.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_SPECIALTY")
public class Especialidade implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", unique = true, length = 130)
    private String nome;

    @Column(name = "service")
    @OneToMany(mappedBy = "especialidade", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Servico> servicos;
}
