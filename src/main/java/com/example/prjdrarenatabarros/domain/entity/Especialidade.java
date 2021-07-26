package com.example.prjdrarenatabarros.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "especialidade",uniqueConstraints={@UniqueConstraint(columnNames={"nome"})})
public class Especialidade implements Serializable {
    private static final long serrialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String nome;

    @OneToMany(mappedBy = "especialidade", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Servico> servicos;
}
