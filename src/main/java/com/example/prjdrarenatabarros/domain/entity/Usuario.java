package com.example.prjdrarenatabarros.domain.entity;

import com.example.prjdrarenatabarros.domain.Enum.CargoColaborador;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"login"})})
public class Usuario implements Serializable {
    private static final long serrialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(length = 5)
    private CargoColaborador cargo;

    @NotEmpty
    @Column(length = 30)
    private String login;

    @NotEmpty
    private String senha;

    @ManyToOne
    @JoinColumn(name = "especialidade_id")
    private Especialidade especialidade;
}
