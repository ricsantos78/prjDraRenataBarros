package com.example.prjdrarenatabarros.domain.dtos;

import com.example.prjdrarenatabarros.domain.Enum.SexoPaciente;
import com.example.prjdrarenatabarros.domain.entity.Atendimento;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class PacienteDto {
    @NotBlank
    private String nome;

    @NotBlank
    @CPF
    private String cpf;

    @NotBlank
    private SexoPaciente sexo;

    @NotBlank
    private String telefone;

    private String cep;

    private String rua;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String uf;

    private String ibge;

    private List<Atendimento> atendimentos;

}
