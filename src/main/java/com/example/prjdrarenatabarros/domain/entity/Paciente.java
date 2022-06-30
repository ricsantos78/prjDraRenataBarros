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
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_PATIENT")
public class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "name", length = 130)
    private String nome;


    @Column(unique = true, length = 14)
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 1)
    private SexoPaciente sexo;

    @Column(name = "phone",length = 15)
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
