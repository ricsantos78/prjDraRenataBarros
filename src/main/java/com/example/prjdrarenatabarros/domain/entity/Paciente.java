package com.example.prjdrarenatabarros.domain.entity;

import com.example.prjdrarenatabarros.domain.Enum.SexoPaciente;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "paciente",uniqueConstraints={@UniqueConstraint(columnNames={"cpf"})})
public class Paciente implements Serializable {

    private static final long serrialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Nome não pode ser nulo")
    private String nome;

    @NotNull(message = "Cpf não pode ser nulo")
    @Column(length = 14)
    @CPF(message = "Cpf inválido")
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private SexoPaciente sexo;

    @NotNull(message = "Telefone não pode ser nulo")
    @Column(length = 15)
    private String telefone;

    private String cep;

    private String rua;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String uf;

    private String ibge;

    @OneToMany(mappedBy = "paciente", orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Atendimento> atendimentos;
}
