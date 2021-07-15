package com.example.prjdrarenatabarros.domain.entity;

import com.example.prjdrarenatabarros.domain.Enum.SexoPaciente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints={@UniqueConstraint(columnNames={"cpf"})})
public class Paciente implements Serializable {

    private static final long serrialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String nome;

    @NotEmpty
    @Column(length = 14)
    @CPF
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private SexoPaciente sexo;

    @Column(length = 15)
    private String telefone;

    @OneToMany(mappedBy = "paciente", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Atendimento> atendimentos;
}
