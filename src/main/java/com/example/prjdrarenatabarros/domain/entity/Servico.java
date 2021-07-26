package com.example.prjdrarenatabarros.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "servico")
public class Servico implements Serializable {

    private static final long serrialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Senha não pode ser vazio")
    @NotNull(message = "Senha não pode ser nulo")
    private String nome;

    @NotEmpty(message = "Senha não pode ser vazio")
    @NotNull(message = "Senha não pode ser nulo")
    private String valor;

    @ManyToOne
    @JoinColumn(name = "especialidade_id")
    private Especialidade especialidade;

}
